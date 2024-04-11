package com.mafraq.data.remote.dataSource.chat

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.toObject
import com.mafraq.data.remote.errors.DeleteException
import com.mafraq.data.remote.errors.FetchException
import com.mafraq.data.remote.errors.InsertException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


inline fun <reified T> CollectionReference.asFlow(): Flow<List<T>> = callbackFlow {
    val eventListener = EventListener<QuerySnapshot> { snapshot, exception ->
        if (exception != null) {
            close(exception)
            return@EventListener
        }

        if (snapshot == null)
            return@EventListener

        val data = snapshot
            .mapNotNull { it.toObject<T>() }

        trySend(data)
    }

    val registration = addSnapshotListener(eventListener)

    awaitClose {
        registration.remove()
    }
}

suspend inline fun <reified T> CollectionReference.fetchAll(): List<T> =
    suspendCoroutine { continuation ->
        get()
            .addOnSuccessListener { snapshot ->
                continuation.resume(snapshot.documents.mapNotNull { it.toObject<T>() })
            }
            .addOnFailureListener { exception ->
                val errorMessage = exception.message ?: "Fetch data failed!"
                continuation.resumeWithException(FetchException(message = errorMessage))
            }
    }


suspend fun<T: Any> CollectionReference.insert(entry: T, id: String): Boolean {
    val dataRef = document(id)
    return suspendCoroutine { continuation ->
        dataRef
            .set(entry)
            .addOnSuccessListener {
                continuation.resume(true)
            }
            .addOnFailureListener { exception ->
                val errorMessage = exception.message ?: "Insert failed!"
                continuation.resumeWithException(InsertException(message = errorMessage))
            }
    }
}

suspend fun CollectionReference.delete(id: String): Boolean {
    val dataRef = document(id)
    return suspendCoroutine { continuation ->
        dataRef
            .delete()
            .addOnSuccessListener {
                continuation.resume(true)
            }
            .addOnFailureListener { exception ->
                val errorMessage = exception.message ?: "Delete failed!"
                continuation.resumeWithException(DeleteException(message = errorMessage))
            }
    }
}