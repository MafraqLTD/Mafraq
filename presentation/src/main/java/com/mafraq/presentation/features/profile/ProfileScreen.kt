package com.mafraq.presentation.features.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.AppContainer
import com.mafraq.presentation.design.components.AppOutlinedTextField
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.DateSelector
import com.mafraq.presentation.design.components.GenderDropdownMenu
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.components.buttons.AppButton
import com.mafraq.presentation.design.components.container.OutlinedContainer
import com.mafraq.presentation.features.profile.components.OffDaysChipset
import com.mafraq.presentation.features.profile.components.PickLocation
import com.mafraq.presentation.navigation.destinations.navigateToMap
import com.mafraq.presentation.utils.extensions.Listen
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string
import com.mafraq.presentation.utils.rememberLocationRequester


@Composable
fun ProfileScreen(viewModel: ProfileViewModel, navController: NavController) {
    val state: ProfileUiState by viewModel.state.collectAsStateWithLifecycle()
    val event: ProfileEvent? by viewModel.event.collectAsState(null)
    val listener: ProfileInteractionListener = viewModel

    var addressId = remember { 0 }
    val locationRequester = rememberLocationRequester(
        onLocationSatisfied = {
            navController.navigateToMap(
                fromProfile = true,
                addressId = addressId
            )
        },
        locationSettingsDelegate = viewModel
    )

    Content(
        state = state,
        listener = listener
    )

    event?.Listen { currentEvent ->
        when (currentEvent) {
            ProfileEvent.OnLogout -> {}
            is ProfileEvent.OnNavigateToMapForHomeAddress,
            is ProfileEvent.OnNavigateToMapForWorkAddress -> {
                addressId = currentEvent.id
                locationRequester.request()
            }
        }
    }
}


@Composable
private fun Content(
    state: ProfileUiState = ProfileUiState(),
    listener: ProfileInteractionListener = ProfileInteractionListener.PreviewInstance
) {
    AppContainer { focusManager ->
        PickLocation(
            label = R.string.home_address.string,
            painter = R.drawable.home_address.painter,
            formattedAddress = state.homeLocation.formattedAddress,
            onClick = listener::onHomeAddressClicked
        )

        Spacer.Medium()

        PickLocation(
            label = R.string.work_address.string,
            painter = R.drawable.work_address.painter,
            formattedAddress = state.workLocation.formattedAddress,
            onClick = listener::onWorkAddressClicked
        )

        Spacer.Medium()

        HorizontalDivider()
        Spacer.Medium()

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
