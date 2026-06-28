package pa.ac.utp.smartroute.ui.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pa.ac.utp.smartroute.data.mock.MockData
import pa.ac.utp.smartroute.data.model.RouteMock
import pa.ac.utp.smartroute.ui.components.SmartRouteCard
import pa.ac.utp.smartroute.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageRoutesScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de rutas simuladas", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(SmartBackground)
        ) {
            Text(
                "Administra las rutas simuladas del sistema.",
                fontSize = 12.sp,
                color = SmartGray,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            
            Button(
                onClick = { /* Visual */ },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SmartBlue)
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Agregar ruta", fontWeight = FontWeight.Bold)
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(MockData.routes) { route ->
                    ManageRouteItem(route)
                }
            }
        }
    }
}

@Composable
fun ManageRouteItem(route: RouteMock) {
    SmartRouteCard {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(SmartLightBlue, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = SmartBlue)
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = route.name, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = SmartText)
                    Spacer(modifier = Modifier.width(8.dp))
                    StatusBadge(text = route.status)
                }
                Text(text = "Código: RUT-00${route.id}", fontSize = 12.sp, color = SmartGray)
                Text(text = "Origen: ${route.origin}", fontSize = 12.sp, color = SmartGray)
                Text(text = "Destino: ${route.destination}", fontSize = 12.sp, color = SmartGray)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = { /* Visual */ },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, SmartBorder)
            ) {
                Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(16.dp), tint = SmartBlue)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Editar", fontSize = 12.sp, color = SmartBlue)
            }
            
            OutlinedButton(
                onClick = { /* Visual */ },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = SmartRed),
                border = androidx.compose.foundation.BorderStroke(1.dp, SmartLightRed)
            ) {
                Icon(Icons.Default.Delete, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Eliminar", fontSize = 12.sp)
            }

            OutlinedButton(
                onClick = { /* Visual */ },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = SmartGreen),
                border = androidx.compose.foundation.BorderStroke(1.dp, SmartLightGreen)
            ) {
                Icon(if (route.status == "Activa") Icons.Default.Block else Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(if (route.status == "Activa") "Inactivar" else "Activar", fontSize = 11.sp)
            }
        }
    }
}

@Composable
fun StatusBadge(text: String) {
    val color = if (text == "Activa") SmartGreen else SmartGray
    val bgColor = if (text == "Activa") SmartLightGreen else Color(0xFFF3F4F6)
    
    Box(
        modifier = Modifier
            .background(bgColor, RoundedCornerShape(4.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(text = text, fontSize = 10.sp, color = color, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ManageRoutesScreenPreview() {
    SmartRouteTheme {
        ManageRoutesScreen(onNavigateBack = {})
    }
}