package pa.ac.utp.smartroute.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pa.ac.utp.smartroute.ui.theme.SmartBlue
import pa.ac.utp.smartroute.ui.theme.SmartGreen
import pa.ac.utp.smartroute.ui.theme.SmartRed

@Composable
fun StatusChip(text: String) {
    val backgroundColor = when {
        text.contains("Activa") || text.contains("Activo") || 
        text.contains("En recorrido") || text.contains("Disponible") -> SmartGreen.copy(alpha = 0.1f)
        text.contains("Inactiva") || text.contains("Inactivo") -> SmartRed.copy(alpha = 0.1f)
        else -> SmartBlue.copy(alpha = 0.1f)
    }
    
    val textColor = when {
        text.contains("Activa") || text.contains("Activo") || 
        text.contains("En recorrido") || text.contains("Disponible") -> SmartGreen
        text.contains("Inactiva") || text.contains("Inactivo") -> SmartRed
        else -> SmartBlue
    }

    Box(
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}