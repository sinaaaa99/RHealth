package com.example.rehealth.ui.screens.main.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.rehealth.navigation.main.ScreensNavItem
import com.example.rehealth.ui.theme.bottomBar_Dark

@Composable
fun CustomBottomBar(navHostController: NavHostController) {


    val screensItem = remember {
        ScreensNavItem.values()
    }

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    val bottomDestination = screensItem.any { it.route == currentDestination?.route }

    if (bottomDestination) {

        Row(
            modifier = Modifier.shadow(elevation = 10.dp)
                .background(color = bottomBar_Dark)
                .padding(4.dp)
                .fillMaxWidth(),
            verticalAlignment = CenterVertically,
            horizontalArrangement = SpaceBetween,
        ) {

            screensItem.forEach { screensItem ->

                val selected = currentDestination?.hierarchy?.any {
                    it.route == screensItem.route
                } == true

                val backgroundColor =
                    if (selected) MaterialTheme.colorScheme.tertiary else bottomBar_Dark

                val contentColor = if (selected) Color.White else Color.Black

                Box(modifier = Modifier
                    .height(40.dp)
                    .clip(CircleShape)
                    .background(backgroundColor)
                    .clickable {

                        navHostController.navigate(screensItem.route) {
                            popUpTo(navHostController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }) {

                    Row(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp),
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        Icon(
                            painter = painterResource(id = if (selected) screensItem.selectedIcon else screensItem.icon),
                            contentDescription = "bottom nav icon",
                            tint = contentColor
                        )

                        AnimatedVisibility(visible = selected) {

                            Text(text = screensItem.title, color = contentColor)
                        }

                    }
                }


            }


        }
    }

}