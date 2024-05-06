package com.mafraq.presentation.features.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.AppOutlinedTextField
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.DateSelector
import com.mafraq.presentation.design.components.GenderDropdownMenu
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.components.buttons.AppButton
import com.mafraq.presentation.design.components.container.AppContainer
import com.mafraq.presentation.design.components.container.OutlinedContainer
import com.mafraq.presentation.design.components.navigation.LocalAppUserType
import com.mafraq.presentation.features.profile.components.OffDaysChipset
import com.mafraq.presentation.features.profile.components.PickLocation
import com.mafraq.presentation.utils.extensions.Listen
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string
import com.mafraq.presentation.utils.rememberLocationRequester


@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    navigateToHome: () -> Unit,
    navigateToMap: (fromProfile: Boolean, addressId: Int) -> Unit,
    navigateToSearch: (fromProfile: Boolean) -> Unit,
    navigateToLogin: () -> Unit,
) {
    val state: ProfileUiState by viewModel.state.collectAsStateWithLifecycle()
    val event: ProfileEvent? by viewModel.event.collectAsState(null)
    val listener: ProfileInteractionListener = viewModel

    var addressId = remember { 0 }
    val locationRequester = rememberLocationRequester(
        onLocationSatisfied = {
            navigateToMap(true, addressId)
        },
        locationSettingsDelegate = viewModel
    )

    Content(
        state = state,
        listener = listener
    )

    event?.Listen { currentEvent ->
        when (currentEvent) {
            ProfileEvent.OnLogout -> navigateToLogin()
            is ProfileEvent.OnNavigateToMapForWorkAddress -> navigateToSearch(true)

            is ProfileEvent.OnNavigateToMapForHomeAddress -> {
                addressId = currentEvent.id
                locationRequester.request()
            }

            ProfileEvent.OnNavigateToHome -> navigateToHome()
        }
    }
}


@Composable
private fun Content(
    state: ProfileUiState = ProfileUiState(),
    listener: ProfileInteractionListener = ProfileInteractionListener.PreviewInstance
) {
    val appUserType = LocalAppUserType.current

    AppContainer(
        actions = {
            IconButton(onClick = listener::onLogout) {
                Icon(
                    painter = R.drawable.logout.painter,
                    contentDescription = null
                )
            }
        }
    ) { focusManager ->
        PickLocation(
            label = R.string.home_address.string,
            painter = R.drawable.home_address.painter,
            formattedAddress = state.homeLocation.formattedAddress,
            onClick = listener::onHomeAddressClicked
        )

        Spacer.Medium()

        if (appUserType.isEmployeeApp) {
            PickLocation(
                label = R.string.work_address.string,
                painter = R.drawable.work_address.painter,
                formattedAddress = state.workLocation.formattedAddress,
                onClick = listener::onWorkAddressClicked
            )

            Spacer.Medium()
        }

        HorizontalDivider()
        Spacer.Medium()

        if (appUserType.isEmployeeApp) {
            OutlinedContainer(
                border = false,
                fieldHeight = false,
                label = R.string.off_days.string
            ) {
                OffDaysChipset(
                    selectedItems = state.offDays,
                    onItemChanged = listener::setOffDays
                )
            }

            Spacer.Medium()

            HorizontalDivider()
            Spacer.Medium()
        }

        AppOutlinedTextField(
            label = R.string.fullname.string,
            value = state.fullName,
            onValueChange = listener::setFullname,
            isError = state.isError,
            errorMessage = state.error?.message,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = R.drawable.user_id.painter
        )

        Spacer.Medium()

        AppOutlinedTextField(
            label = R.string.phone.string,
            value = state.phone,
            onValueChange = listener::setPhone,
            isError = state.isError,
            errorMessage = state.error?.message,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Number,
            leadingIcon = R.drawable.phone.painter
        )

        Spacer.Medium()

        AppOutlinedTextField(
            label = R.string.email.string,
            value = state.email,
            onValueChange = listener::setEmail,
            isError = state.isError,
            errorMessage = state.error?.message,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = R.drawable.email.painter
        )

        Spacer.Medium()

        if (appUserType.isDriverApp) {
            AppOutlinedTextField(
                label = R.string.nationalId.string,
                value = state.nationalId,
                onValueChange = listener::setNationalId,
                isError = state.isError,
                errorMessage = state.error?.message,
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = R.drawable.user_id.painter
            )

            Spacer.Medium()

            AppOutlinedTextField(
                label = R.string.car_name.string,
                value = state.carName,
                onValueChange = listener::setCarName,
                isError = state.isError,
                errorMessage = state.error?.message,
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = R.drawable.car_name.painter
            )

            Spacer.Medium()

            AppOutlinedTextField(
                label = R.string.car_number.string,
                value = state.carNumber,
                onValueChange = listener::setCarNumber,
                isError = state.isError,
                errorMessage = state.error?.message,
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = R.drawable.car_number.painter
            )

            Spacer.Medium()

            AppOutlinedTextField(
                label = R.string.snippet.string,
                value = state.snippet,
                onValueChange = listener::setSnippet,
                isError = state.isError,
                errorMessage = state.error?.message,
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = R.drawable.snippet.painter
            )

            Spacer.Medium()

        }

        GenderDropdownMenu(
            value = state.gender,
            onSelected = listener::setGender
        )

        Spacer.Medium()

        DateSelector(
            label = R.string.birthday.string,
            painter = R.drawable.calendar_date.painter,
            initialDate = state.birthday,
            onDateSelected = { selectedDate ->
                listener.setBirthday(selectedDate.toString())
            }
        )

        Spacer.Large()

        AppButton(
            text = R.string.save.string,
            onClick = {
                listener.onSave()
                focusManager.clearFocus()
            },
            loading = state.isLoading,
            enabled = listener.validateFields(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun Preview() {
    ColumnPreview { Content() }
}
