package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.mafraq.data.entities.AppUserType
import com.mafraq.data.entities.chat.Message
import com.mafraq.data.entities.profile.GeneralProfile
import com.mafraq.data.local.profile.DriverProfileLocalDataSourceImpl
import com.mafraq.data.local.profile.EmployeeProfileLocalDataSourceImpl
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.remote.models.chat.MessageRemote
import timber.log.Timber
import javax.inject.Inject


class MessageToRemoteMapper @Inject constructor(
    appUserType: AppUserType,
    sessionLocalDataSource: SessionLocalDataSource,
    generalProfileFromDriverMapper: GeneralProfileFromDriverMapper,
    generalProfileFromEmployeeMapper: GeneralProfileFromEmployeeMapper,
    driverProfileLocalDataSourceImpl: DriverProfileLocalDataSourceImpl,
    employeeProfileLocalDataSourceImpl: EmployeeProfileLocalDataSourceImpl,
) : Mapper<Message, MessageRemote> {
    private val profile: GeneralProfile? = if (appUserType.isDriverApp)
        driverProfileLocalDataSourceImpl.get()?.let(generalProfileFromDriverMapper::map)
    else
        employeeProfileLocalDataSourceImpl.get()?.let(generalProfileFromEmployeeMapper::map)
    private val userId = requireNotNull(sessionLocalDataSource.get()?.email)

    override fun map(from: Message): MessageRemote = from.run {
        Timber.w("Profile: $profile")
        MessageRemote(
            id = id,
            content = content,
            senderName = profile?.fullName.orEmpty(),
            senderImageUrl = profile?.profilePictureUrl.orEmpty(),
            read = isRead,
            senderId = profile?.email ?: userId,
        )
    }
}
