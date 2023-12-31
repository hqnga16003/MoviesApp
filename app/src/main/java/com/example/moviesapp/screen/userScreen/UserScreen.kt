package com.example.moviesapp.screen.userScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Scaffold
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviesapp.R
import com.example.moviesapp.presentation.signIn.GoogleAuthUiClient
import com.example.myapplication.screen.mainScreen.MainViewModel
import com.example.petadoption.bottomnav.BottomBar
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    googleAuthUiClient: GoogleAuthUiClient,
    onSignOut : () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.Black),
                title = {
                    Text(
                        text = "Tài khoản",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
            )
        },
        bottomBar = {
            BottomBar(
                mainViewModel = mainViewModel,
                navController = navController,
                bottomBarState = true
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(Color.Black),
            verticalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color(0xFF12315F))
                    .padding(15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Text(
                    text = "Hãy tham gia Galaxy để thưởng thức hàng ngàn nội dung đặc sắc",
                    fontSize = 15.sp,
                    color = Color.White,
                )
                OutlinedButton(
                    onClick = {
                        if (googleAuthUiClient.getSignedInUser() == null) {
                            navController.navigate("signIn")
                        }
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                )
                {
                    Text(
                        text = if (googleAuthUiClient.getSignedInUser() == null) "Tiếp tục với Galaxy Play" else "Chào mừng đến với Galaxy Play",
                        textAlign = TextAlign.Justify,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(colorResource(id = R.color.dark))
                        .padding(10.dp)
                        .clickable {
                            if (googleAuthUiClient.getSignedInUser() != null) {
                                navController.navigate("profile")
                            } else {
                                navController.navigate("signIn")

                            }
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.iconuser),
                        contentDescription = null,
                        tint = colorResource(id = R.color.whiteBlur),
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "Thiết lập tài khoản",
                            fontSize = 17.sp,
                            color = Color.White,
                        )

                        if (googleAuthUiClient.getSignedInUser() == null) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                                modifier = Modifier.size(38.dp),
                                tint = colorResource(id = R.color.whiteBlur)
                            )
                        } else {
                            Text(
                                text = googleAuthUiClient.getSignedInUser()!!.username.toString(),
                                fontSize = 14.sp,
                                color = colorResource(id = R.color.whiteBlur),
                            )
                        }
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(colorResource(id = R.color.dark))
                        .padding(10.dp)
                        .clickable {
                            if (googleAuthUiClient.getSignedInUser() != null) {
                                navController.navigate("favourite")
                            } else {
                                navController.navigate("signIn")
                            }
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = colorResource(id = R.color.whiteBlur),
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "Danh sách phim yêu thích",
                            fontSize = 17.sp,
                            color = Color.White,
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp),
                            tint = colorResource(id = R.color.whiteBlur)
                        )
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(colorResource(id = R.color.dark))
                        .padding(10.dp)
                        .clickable {
                            if (googleAuthUiClient.getSignedInUser() != null) {

                            } else {
                                navController.navigate("signIn")

                            }
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        tint = colorResource(id = R.color.whiteBlur),
                    )
                    Text(
                        text = "Cài đặt ứng dụng",
                        fontSize = 17.sp,
                        color = Color.White,
                        modifier = Modifier.weight(1f)

                    )
                    IconButton(onClick = { })
                    {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp),
                            tint = colorResource(id = R.color.whiteBlur)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(colorResource(id = R.color.dark))
                        .padding(10.dp)
                        .clickable {
                            if (googleAuthUiClient.getSignedInUser() != null) {

                            } else {
                                navController.navigate("signIn")

                            }
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                )
                {
                    Icon(
                        painter = painterResource(id = R.drawable.iconticket),
                        contentDescription = null,
                        tint = colorResource(id = R.color.whiteBlur),
                    )
                    Text(
                        text = "Ưu đãi của bạn",
                        fontSize = 17.sp,
                        color = Color.White,
                        modifier = Modifier.weight(1f)

                    )
                    androidx.compose.material3.TextButton(onClick = { }) {
                        Text(
                            text = "Nhập Mã",
                            fontSize = 12.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                if (googleAuthUiClient.getSignedInUser() != null) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(colorResource(id = R.color.dark))
                            .padding(10.dp)
                            .clickable {
                                onSignOut()
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = null,
                            tint = colorResource(id = R.color.whiteBlur),
                        )
                        Text(
                            text = "Đăng xuất",
                            fontSize = 17.sp,
                            color = Color.White,
                        )
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Version 0.0.0",
                    fontSize = 17.sp,
                    color = Color(0xFF6B6B6B)
                )
                Text(
                    text = "Hotline: 123456789",
                    fontSize = 17.sp,
                    color = Color(0xFF919191)
                )
            }
        }

    }
}



