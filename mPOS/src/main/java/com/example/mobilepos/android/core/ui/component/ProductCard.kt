import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilepos.core.ui.padding.POSPadding
import com.example.mobilepos.domain.model.Product

@Composable
fun ProductCard(
    product: Product,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .background(backgroundColor, shape = RoundedCornerShape(POSPadding.DEFAULT.dp))
            .clickable(onClick = onClick)
            .padding(POSPadding.DEFAULT.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = product.name,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = POSPadding.MEDIUM.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = product.price.toString(),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}