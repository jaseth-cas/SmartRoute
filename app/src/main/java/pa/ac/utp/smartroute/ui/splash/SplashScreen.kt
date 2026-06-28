package pa.ac.utp.smartroute.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pa.ac.utp.smartroute.ui.components.PrimaryButton
import pa.ac.utp.smartroute.ui.theme.SmartBlue
import pa.ac.utp.smartroute.ui.theme.SmartRouteTheme

@Composable
fun SplashScreen(onStartClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            
            // Design Graphic Simulation
            Box(
                modifier = Modifier
                    .size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                // Pin Graphic
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    tint = SmartBlue
                )
                Icon(
                    imageVector = Icons.Default.DirectionsBus,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp).offset(y = (-15).dp),
                    tint = Color.White
                )
            }
            
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "SmartRoute",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = SmartBlue
                )
                
                Text(
                    text = "Monitoreo inteligente de\nbuses universitarios",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp,
                    color = Color.Gray
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Pagination Dots
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(SmartBlue))
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color.LightGray))
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color.LightGray))
                }
            }
            
            PrimaryButton(
                text = "Comenzar",
                icon = Icons.AutoMirrored.Filled.ArrowForward,
                onClick = onStartClick,
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    SmartRouteTheme {
        SplashScreen(onStartClick = {})
    }
}