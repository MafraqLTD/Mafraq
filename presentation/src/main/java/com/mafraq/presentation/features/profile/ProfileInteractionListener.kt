package com.mafraq.presentation.features.profile


interface ProfileInteractionListener {
    fun onLogout()
    fun onSave()
    fun setEmail(value: String)
    fun setAddress(value: String)
    fun setUsername(value: String)
    fun setPhone(value: String)
    fun setFullname(value: String)
    fun validateFields(): Boolean

    object PreviewInstance: ProfileInteractionListener {
        override fun onLogout() {}
        override fun onSave() {}
        override fun setEmail(value: String) {}
        override fun setAddress(value: String) {}
        override fun setUsername(value: String) {}
        override fun setPhone(value: String) {}
        override fun setFullname(value: String) {}
        override fun validateFields(): Boolean = true
    }
}
