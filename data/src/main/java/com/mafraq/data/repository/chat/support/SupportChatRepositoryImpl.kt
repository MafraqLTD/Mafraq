package com.mafraq.data.repository.chat.support

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mafraq.data.remote.dataSource.chat.FirebaseFireStoreDelegation
import com.mafraq.data.remote.dataSource.chat.FirebaseFireStoreDelegationImpl
import javax.inject.Inject


class SupportChatRepositoryImpl @Inject constructor(
    firestore: FirebaseFirestore,
    firebaseFireStoreDelegation: FirebaseFireStoreDelegationImpl
) : SupportChatRepository, FirebaseFireStoreDelegation by firebaseFireStoreDelegation {
    override val chatCollection: CollectionReference by lazy {
        firestore.collection(SUPPORT_CHAT_COLLECTION)
    }

    private companion object {
        const val SUPPORT_CHAT_COLLECTION = "Support"
    }
}
