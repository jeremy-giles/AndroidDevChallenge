/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.model.Hobbies
import com.example.androiddevchallenge.model.Villager
import com.example.androiddevchallenge.model.villagers
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    NavGraph()
}

@Composable
fun Villagers(
    villagers: List<Villager>,
    selectVillager: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Adopt a villager",
                        fontFamily = FontFamily(
                            Font(R.font.hammersmith_one_regular)
                        ),
                        fontSize = 22.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_tent),
                            contentDescription = "Menu"
                        )
                    }
                }

            )
        }
    ) {
        Surface(color = MaterialTheme.colors.background) {
            LazyColumn(modifier.fillMaxWidth()) {
                items(villagers) { villager ->
                    VillagerItem(villager) { selectVillager(villager.id) }
                }
            }
        }
    }
}

@Composable
fun VillagerItem(villager: Villager, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.surface),
        elevation = 4.dp
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            CoilImage(
                data = villager.iconUrl,
                contentDescription = "icon",
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = villager.txtColor)
            )
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
            ) {
                val (name, birthday, hobby, hobbyValue, gender) = createRefs()

                Text(
                    text = villager.name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.constrainAs(name) {
                        top.linkTo(parent.top)
                    }
                )
                Image(
                    painter = painterResource(
                        id = if (villager.gender == "M") {
                            R.drawable.ic_male
                        } else {
                            R.drawable.ic_female
                        }
                    ),
                    contentDescription = "Gender image",
                    modifier = Modifier
                        .constrainAs(gender) {
                            top.linkTo(name.top)
                            end.linkTo(parent.end)
                        }
                        .width(22.dp),
                )
                Row(
                    modifier = Modifier.constrainAs(birthday) {
                        top.linkTo(name.bottom, 4.dp)
                    },
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cake),
                        contentDescription = "Cake image",
                        modifier = Modifier.width(20.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        villager.birthday,
                        style = MaterialTheme.typography.subtitle1,
                    )
                }
                Text(
                    text = "Hobby:",
                    modifier = Modifier.constrainAs(hobby) {
                        top.linkTo(birthday.bottom, margin = 8.dp)
                    }
                )
                Text(
                    text = villager.hobbies.name.toLowerCase().capitalize(),
                    modifier = Modifier
                        .constrainAs(hobbyValue) {
                            baseline.linkTo(hobby.baseline)
                            start.linkTo(hobby.end, 4.dp)
                        },
                    style = MaterialTheme.typography.subtitle1
                )
            }

        }
    }
}

@Composable
fun VillagerDetail(villagerId: Long) {
    val villager = villagers.first { it -> it.id == villagerId }
    Surface() {
        Column {
            /*Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f)
                    .padding(28.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = villager.txtColor)
                    .padding(16.dp)
            ) {
                CoilImage(
                    data = villager.imageUrl, contentDescription = "icon",
                    modifier = Modifier
                        //.background(color = villager.txtColor)
                        .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                )
            }*/
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f)
                    .background(color = MaterialTheme.colors.surface)
                    .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            ) {
                CoilImage(
                    data = villager.imageUrl, contentDescription = "icon",
                    contentScale = ContentScale.Crop
                )
            }
            Surface(
                Modifier
                    .fillMaxWidth()
                    .weight(4f)
                    .background(MaterialTheme.colors.background)
                    .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            )
            {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.surface)
                        .padding(16.dp)
                ) {
                    Text(text = villager.name, style = MaterialTheme.typography.h4)
                    Row(
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_cake),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                            contentDescription = "Cake image",
                            modifier = Modifier.width(20.dp),
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            villager.birthday,
                            style = MaterialTheme.typography.subtitle1,
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(text = "Sex")
                                Image(
                                    painter = painterResource(
                                        id = if (villager.gender == "M") {
                                            R.drawable.ic_male
                                        } else {
                                            R.drawable.ic_female
                                        }
                                    ),
                                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                                    contentDescription = "Gender image",
                                    modifier = Modifier
                                        .width(40.dp)
                                        .height(40.dp),
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(text = "Hobby")
                                Image(
                                    painter = painterResource(
                                        when (villager.hobbies) {
                                            Hobbies.PLAY -> R.drawable.ic_sports_esports
                                            Hobbies.FASHION -> R.drawable.ic_fashion
                                            Hobbies.FITNESS -> R.drawable.ic_fitness
                                            Hobbies.NATURE -> R.drawable.ic_nature
                                            Hobbies.EDUCATION -> R.drawable.ic_school
                                            Hobbies.MUSIC -> R.drawable.ic_music_note
                                        }
                                    ),
                                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                                    contentDescription = "Hobby image",
                                    modifier = Modifier
                                        .width(40.dp)
                                        .height(40.dp),
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(text = "Personality")
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Snooty",
                                    fontSize = 22.sp,
                                    color = MaterialTheme.colors.onSurface
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "\"${villager.catchphrase}\"",
                                textAlign = TextAlign.Center,
                                fontSize = 26.sp,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.W200
                            )
                        }
                    }


                }
            }
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(MaterialTheme.colors.background)
            ) {
                Box(modifier = Modifier.padding(end = 16.dp)) {
                    ExtendedFloatingActionButton(
                        icon = {
                            Icon(
                                ImageVector.vectorResource(id = R.drawable.ic_tent),
                                "Tent icon"
                            )
                        },
                        text = { Text("Adopt Me") },
                        backgroundColor = MaterialTheme.colors.secondary,
                        onClick = { /*do something*/ },
                        elevation = FloatingActionButtonDefaults.elevation(8.dp),
                        modifier = Modifier
                            .width(160.dp)
                            .height(45.dp)
                    )
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
