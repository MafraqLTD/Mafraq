package com.mafraq.data.errors

import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException


abstract class NetworkExceptions(message: String) : IOException(message)

class InternetDisconnectedException : NetworkExceptions("No Internet Access!")

class ConnectionException : ConnectException("Connection Failed!")

class TimeoutException : SocketTimeoutException("Connection Timeout!")

class UnAuthorizedException : NetworkExceptions("Status Code 401")

class EmptyBodyException : NetworkExceptions("Empty Body Exception")
