package com.example.geofencealertmini

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent) ?: return

        if (geofencingEvent.hasError()) {
            val errorMessage = GeofenceStatusCodes.getStatusCodeString(geofencingEvent.errorCode)
            Toast.makeText(context, "Geofence Error: $errorMessage", Toast.LENGTH_SHORT).show()
            return
        }

        // Get transition type (ENTER or EXIT)
        val geofenceTransition = geofencingEvent.geofenceTransition

        when (geofenceTransition) {
            Geofence.GEOFENCE_TRANSITION_ENTER -> {
                Toast.makeText(context, "🟢 ENTERED Geofence Area!", Toast.LENGTH_LONG).show()
            }
            Geofence.GEOFENCE_TRANSITION_EXIT -> {
                Toast.makeText(context, "🔴 EXITED Geofence Area!", Toast.LENGTH_LONG).show()
            }
            else -> {
                Toast.makeText(context, "Unknown Geofence Transition", Toast.LENGTH_SHORT).show()
            }
        }
    }
}