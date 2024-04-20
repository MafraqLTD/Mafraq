package com.mafraq.presentation.features.profile

import com.mafraq.data.entities.map.Location
import com.mafraq.data.entities.profile.DayOff
import com.mafraq.data.entities.profile.Gender


interface ProfileInteractionListener {
    fun onLogout()
    fun onSave()
    fun setEmail(value: String)
    fun setHomeAddress(value: Location)
    fun setWorkAddress(value: Location)
    fun setPhone(value: String)
    fun setFullname(value: String)
    fun validateFields(): Boolean
    fun setGender(value: Gender)
    fun setOffDays(selected: Boolean, offDay: DayOff)
    fun setBirthday(value: String)

    object PreviewInstance: ProfileInteractionListener {
        override fun onLogout() {}
        override fun onSave() {}
        override fun setEmail(value: String) {}
        override fun setBirthday(value: String) {}
        override fun setHomeAddress(value: Location) {}
        override fun setWorkAddress(value: Location) {}
        override fun setPhone(value: String) {}
        override fun setFullname(value: String) {}
        override fun validateFields(): Boolean = true
        override fun setGender(value: Gender) = Unit
        override fun setOffDays(selected: Boolean, offDay: DayOff) = Unit
    }
}
