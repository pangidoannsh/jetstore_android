package com.pangidoannsh.jetstore.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pangidoannsh.jetstore.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    isHome: Boolean = false,
    isAbout: Boolean = false,
    navigateToAbout: () -> Unit,
    navigateToBack: () -> Unit,
    query: String,
    onQueryChange: (query: String) -> Unit,
    onSearch: () -> Unit
) {
    if (isHome) {
        Search(query, onQueryChange,onSearch, navigateToAbout)
    } else {
        CenterAlignedTopAppBar(
            actions = {
                if (!isAbout) {
                    IconButton(onClick = { navigateToAbout() }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle, contentDescription = "about",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            },
            title = {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = { navigateToBack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back home",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        )
    }
}