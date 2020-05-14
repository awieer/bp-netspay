package com.berrypay.bps

import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity: FlutterActivity() {

    companion object {
        const val CHANNEL = "com.berrypay.bps.channel"
        const val KEY_NATIVE = "showNativeView"
    }

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);

        initFlutterChannel()
    }

    private fun initFlutterChannel() {
        val channel = MethodChannel(getFlutterEngine()?.getDartExecutor()?.getBinaryMessenger(), CHANNEL)
        channel.setMethodCallHandler { methodCall, result ->
            val args = methodCall.arguments as List<*>
            val amount = args.first() as String
            val txnRef = args[1] as String

            if (methodCall.method == KEY_NATIVE) {
                val intent = Intent(this, NetsActivity::class.java)
                intent.putExtra("amount", amount);
                intent.putExtra("txn_ref", txnRef);
                intent.putExtra("umid", "UMID_857972002");
                intent.putExtra("api_key", "623f4af1-8d93-451f-b9bb-e483f3d1ff46");
                intent.putExtra("secret_key", "3427c054-5886-4d46-9cd8-23191201c0d4");
                startActivity(intent)
                result.success(true)
            } else {
                result.notImplemented()
            }
        }
    }
}
