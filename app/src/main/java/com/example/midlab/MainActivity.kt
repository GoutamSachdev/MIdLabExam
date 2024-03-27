package com.example.midlab

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChip
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.midlab.ui.theme.MidLabTheme
import kotlinx.coroutines.delay
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MidLabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val variable = remember { mutableStateOf("") }
    NavHost(navController = navController, startDestination = "SplashScreen") {
        composable("SplashScreen") {
            SplashScreen(navController = navController,onSignUpClick = {
                navController.navigate("SplashScreen")
            })
        }
        composable("citySelection") {
            citySelection(navController = navController,onButtonClick = {
                navController.navigate("LoginScreen")
            })
        }
        composable("weatherDetails") {
            weatherDetails(navController = navController,onButtonClick = {
                navController.navigate("PersonListScreen")
            },variable=variable)
        }



    }
}
@Composable
fun SplashScreen(navController: NavController, onSignUpClick: () -> Unit){
    LaunchedEffect(Unit) {

        delay(300) // Wait for 3 seconds

        navController.navigate("citySelection")

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        
        
    )
    {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Sky Sight")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun citySelection(navController: NavController,onButtonClick: () -> Unit) {
    var City by remember { mutableStateOf("") }
    Scaffold(
        topBar = {


            TopAppBar(title = {   Text("Select CIty")})

        },
        bottomBar = {BottomAppBar {

            AssistChip(onClick = { /*TODO*/ },
                label = {Text(text = "")},
                leadingIcon = {

                    Icon(Icons.Default.Favorite,contentDescription = "shooping")
                })



        }}
        ) {

        it-> Column (modifier= Modifier.padding(it), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ){
        Image(

            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .fillMaxHeight()

                .padding(40.dp, 10.dp, 0.dp, 0.dp,)

                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.background),


        )
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.map),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(45.dp, 10.dp, 0.dp, 0.dp,)
                .fillMaxHeight()

                .background(MaterialTheme.colorScheme.background)
        )
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            value = City,
            onValueChange = { City = it },
            label = { Text("Enter city name") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier

                .padding(45.dp, 10.dp, 0.dp, 0.dp,)
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.surface)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {


                navController.navigate("weatherDetails",City)
            },
            modifier = Modifier
                .padding(45.dp, 10.dp, 0.dp, 0.dp,)
                .height(48.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(Color.Cyan)
        ) {
            Text("Show Weather", color = Color.White)
        }

    }


    }

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun weatherDetails(navController: NavController,onButtonClick: () -> Unit,variable: MutableState<String>){
    Scaffold(
        topBar = {


            TopAppBar(title = {   Text(" Weather Detail")})

        },
        bottomBar = {BottomAppBar {

            AssistChip(onClick = { /*TODO*/ },
                label = {Text(text = "")},
                leadingIcon = {

                    Icon(Icons.Default.Favorite,contentDescription = "shooping")
                })



        }}
    ){
        it->
        Column (modifier= Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Spacer(modifier = Modifier.height(200.dp))
            Text(text = "City Name: ")
            Row(
                modifier = Modifier
                    .shadow(4.dp, shape = RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)


            ) {
                // Column with Image (RoundedCorner)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 30.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }

                // Column with two TextFields (Name and Phone Number)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(end = 10.dp)
                ) {
                    // Name TextField
                    Text(
                        text = "",
                        fontWeight = FontWeight.W900,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 2.dp)
                    )

                    // Phone Number TextField
                    Text(
                        text = "Age $",
                        fontWeight = FontWeight.ExtraLight,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                }
            }
        }


    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MidLabTheme {

    }
}