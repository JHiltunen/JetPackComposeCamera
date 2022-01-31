package com.jhiltunen.composecamera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jhiltunen.composecamera.ui.theme.ComposeCameraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCameraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GetThumbnail()
                }
            }
        }
    }
}

@Composable
fun GetThumbnail() {
    val result = remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        result.value = it
    }

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        IconButton(onClick = { launcher.launch() }, modifier = Modifier.fillMaxWidth()) {
            Row() {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Menu"
                )
                Text(text = "Take a picture")
            }
        }
        Spacer(Modifier.height(16.dp))
        result.value?.let { image ->
            Image(bitmap = image.asImageBitmap(), contentDescription = null, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight())
        }
    }
}