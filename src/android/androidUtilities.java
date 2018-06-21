package cordova.plugin.android.utilities;

import android.graphics.Color;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class androidUtilities extends CordovaPlugin {
    private CallbackContext _callbackContext;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
      this._callbackContext = callbackContext;
    if (action.equals("enableLayoutFlags")) {
        int value = args.getInt(0);
        enableLayoutFlags(value,callbackContext);
        return true;
    }
    if(action.equals("changeKeyboardType")){
        int value = args.getInt(0);
        changeKeyboardType(value,callbackContext);
        return true;
    }
    if(action.equals("setColorNavigationBar")){
        String value = args.getString(0);
        setColorNavigationBar(value,callbackContext);
        return true;
    }
    if(action.equals("setTranslucent")){
        int value = args.getInt(0);
        setTranslucent(value,callbackContext);
        return true;
    }

    return false;
    }

    public void  enableLayoutFlags(final int type, final CallbackContext callbackContext){
      if(type > 0) {
        switch(type){
          case 1 :  enable();
                           callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                           break;
          case 2 : disable();
                           callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                           break;
        }
      }else{
        this._callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
      }
    }

    public void changeKeyboardType(final int keyboardType,final CallbackContext callbackContext){
        if(keyboardType > 0) {
          cordova.getActivity().runOnUiThread(new Runnable() {
              @Override
              public void run() {
                cordova.getActivity().getWindow().setSoftInputMode(keyboardType);
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                }
          });
        }else{
            this._callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
        }
    }

    public void enable(){
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
          cordova.getActivity().runOnUiThread(new Runnable() {
              @Override
              public void run() {
                cordova.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);                
                  cordova.getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
              }
          });
      }
    }

    public void disable(){
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
          cordova.getActivity().runOnUiThread(new Runnable() {
              @Override
              public void run() {
                cordova.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                cordova.getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
              }
          });
      }
    }

    public void setColorNavigationBar(final String color, final CallbackContext callbackContext){
        final Window window = cordova.getActivity().getWindow();
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
               cordova.getActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
  
                       //cordova.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
                       try {
                         window.getClass().getMethod("setStatusBarColor", int.class).invoke(window, Color.parseColor(color));
                       } catch (IllegalAccessException e) {
                         e.printStackTrace();
                       } catch (InvocationTargetException e) {
                         e.printStackTrace();
                       } catch (NoSuchMethodException e) {
                         e.printStackTrace();
                       }
                       //cordova.getActivity().getWindow().setStatusBarColor(Integer.parseInt(color));
  
                     callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                   }
               });
           }
           callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
      }


      public void setTranslucent(final int type, final CallbackContext callbackContext){
        if(type > 0) {
          if(type == 1){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
              cordova.getActivity().runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                    cordova.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                    cordova.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                  }
              });
          }
          }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
              cordova.getActivity().runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                    cordova.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    cordova.getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                  }
              });
          }  
          }
        }else{
          this._callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
        }
      }
}
