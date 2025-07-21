package com.example.ecommerceapp.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ecommerceapp.core.session.SessionManager
import com.example.ecommerceapp.design.Theme
import com.example.ecommerceapp.designsystem.atoms.EcommercePrimaryButton
import com.example.ecommerceapp.designsystem.organisms.AppTopBar
import com.example.ecommerceapp.designsystem.theme.Dimensions
import com.example.ecommerceapp.feature.auth.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            AppTopBar(title = "Profile", showBackButton = false)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = Dimensions.spacing_16),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Profile Screen",
                    style = Theme.titleLarge
                )

                Spacer(modifier = Modifier.height(32.dp))

                EcommercePrimaryButton(onClick = onLogout) {
                    Text("Logout")
                }
            }
        }
    }
}
