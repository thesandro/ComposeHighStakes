package com.example.freshcomposeproject.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DropDownList(
    list: List<String>,
    request: (Boolean) -> Unit,
    modifier: Modifier,
    selectedString: (String) -> Unit
) {
    val text = remember { mutableStateOf("") } // initial value
    val isOpen = remember { mutableStateOf(false) } // initial value
    Box(
        modifier = modifier
    ) {
        Column {
            TextField(
                value = text.value,
                onValueChange = { text.value = it },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                label = { Text(text = "Tags") },
            )
            DropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = isOpen.value,
                onDismissRequest = {
                    isOpen.value = false
                    request(false)
                },
            ) {
                list.forEach {
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            isOpen.value = false
                            text.value = it
                            selectedString(it)
                        }
                    ) {
                        Text(it, modifier = Modifier.wrapContentWidth())
                    }
                }
            }
        }
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(2.dp)
                .clickable(
                    onClick = { isOpen.value = true }
                )
        )
    }
}