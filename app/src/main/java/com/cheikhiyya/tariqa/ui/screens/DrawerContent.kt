package com.cheikhiyya.tariqa.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cheikhiyya.tariqa.R
import com.cheikhiyya.tariqa.data.MENU
import com.cheikhiyya.tariqa.data.MenuNode
import com.cheikhiyya.tariqa.ui.theme.BorderColor
import com.cheikhiyya.tariqa.ui.theme.Cream
import com.cheikhiyya.tariqa.ui.theme.Gold
import com.cheikhiyya.tariqa.ui.theme.GoldLight
import com.cheikhiyya.tariqa.ui.theme.Primary
import com.cheikhiyya.tariqa.ui.theme.TextDark
import com.cheikhiyya.tariqa.ui.theme.TextMuted

@Composable
fun DrawerContent(onNavigate: (String) -> Unit) {
    ModalDrawerSheet(drawerContainerColor = Cream) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Primary)
                .padding(top = 40.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_placeholder),
                contentDescription = "شعار الطريقة الشيخية",
                modifier = Modifier.size(64.dp),
            )
            Spacer(Modifier.height(8.dp))
            Text("الطريقة الشيخية الشاذلية", color = Gold, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            Text("Tariqa Cheikhiyya", color = GoldLight)
        }
        LazyColumn {
            items(MENU) { node ->
                MenuNodeRow(node = node, depth = 0, onNavigate = onNavigate)
            }
        }
    }
}

@Composable
private fun MenuNodeRow(node: MenuNode, depth: Int, onNavigate: (String) -> Unit) {
    var open by remember { mutableStateOf(false) }
    val hasChildren = node.children != null

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    if (hasChildren) {
                        open = !open
                    } else {
                        node.articleId?.let(onNavigate)
                    }
                }
                .padding(start = 12.dp, end = (16 + depth * 16).dp, top = 12.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                node.title,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Right,
                color = TextDark,
                fontWeight = if (depth == 0) FontWeight.Bold else FontWeight.Normal,
            )
            if (hasChildren) {
                Icon(
                    if (open) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = null,
                    tint = TextMuted,
                )
            }
        }
        HorizontalDivider(color = BorderColor)
        if (hasChildren && open) {
            node.children!!.forEach { child ->
                MenuNodeRow(node = child, depth = depth + 1, onNavigate = onNavigate)
            }
        }
    }
}
