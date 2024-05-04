package com.mafraq.presentation.features.profile

import com.mafraq.data.entities.profile.DayOff
import com.mafraq.data.entities.profile.Gender


interface ProfileInteractionListener {
    fun onSave()
    fun onLogout()
    fun setEmail(value: String)
    fun onHomeAddressClicked()
    fun onWorkAddressClicked()
    fun setPhone(value: String)
    fun setFullname(value: String)
    fun setNationalId(value: String)
    fun setCarName(value: String)
    fun setCarNumber(value: String)
    fun setSnippet(value: String)
    fun validateFields(): Boolean
    fun setGender(value: Gender)
    fun setOffDays(selected: Boolean, offDay: DayOff){}
    fun setBirthday(value: String)

    object PreviewInstance: ProfileInteractionListener {
        override fun onLogout() {}
        override fun onSave() {}
        override fun setEmail(value: String) {}
        override fun setBirthday(value: String) {}
        override fun onHomeAddressClicked() {}
        override fun onWorkAddressClicked() {}
        override fun setPhone(value: String) {}
        override fun setFullname(value: String) {}
        override fun setNationalId(value: String) {}
        override fun setCarName(value: String) {}
        override fun setCarNumber(value: String) {}
        override fun setSnippet(value: String) {}
        override fun validateFields(): Boolean = true
        override fun setGender(value: Gender) = Unit
    }
}
