package pa.ac.utp.smartroute.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pa.ac.utp.smartroute.ui.theme.SmartText

@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier.padding(vertical = 8.dp),
        style = MaterialTheme.typography.titleMedium,
        color = SmartText,
        fontWeight = FontWeight.Bold
    )
}