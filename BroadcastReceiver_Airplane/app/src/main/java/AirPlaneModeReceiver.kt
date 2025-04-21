import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast

class AirPlaneModeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
//        if(intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED){
////            val isTurnedOn= Settings.Global.getInt( context?.contentResolver, Settings.Global.AIRPLANE_MODE_ON) !=0
//            val isTurnedOn= intent.getBooleanExtra("state", false)
//        Toast.makeText(context, "Airplane Mode: $isTurnedOn", Toast.LENGTH_SHORT).show()
        when(intent?.action){
            "com.example.br_powerreceiver.ACTION_CUSTOM_BROADCAST"-> Toast.makeText(context, "My Custom Broadcast received", Toast.LENGTH_SHORT).show()

        }
    }

}