@Composable
fun SimpleButton() {
    val context = LocalContext.current
    Column {
        Button(onClick = {
            context.sendMail(to = "email@gmail.com", subject = "Contatto")
        },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722),contentColor = Color.Black)) {
            Text(text = "Email")
        }

        Button(onClick = {
            context.dial(phone = "12345678")
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722), contentColor = Color.Black)
        ) {
            Text(text = "Telefono")
        }
    }
}

fun Context.sendMail(to: String, subject: String) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "vnd.android.cursor.item/email" // or "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        // TODO: Handle case where no email app is available
    } catch (t: Throwable) {
        // TODO: Handle potential other type of exceptions
    }
}

fun Context.dial(phone: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    } catch (t: Throwable) {
        // TODO: Handle potential exceptions
    }
}
