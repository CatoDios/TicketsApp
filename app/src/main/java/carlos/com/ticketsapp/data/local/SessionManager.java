package carlos.com.ticketsapp.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import carlos.com.ticketsapp.data.models.AccessTokenEntity;
import carlos.com.ticketsapp.data.models.UserEntity;

/**
 * Created by kath on 09/04/18.
 */

public class SessionManager {
    public static final String PREFERENCE_NAME = "SymbiosisClient";
    public static int PRIVATE_MODE = 0;

    /**
     USUARIO DATA SESSION - JSON
     */
    public static final String USER_TOKEN = "user_code";
    public static final String USER_JSON = "user_json";
    public static final String IS_LOGIN = "user_login";
    public static final String USER_MAIL = "user_mail";
    public static final String ID_COMIDA="id_comida";
    public static final String TURNO="turno";
    public static final String NIVEL="nivel";
    public static final String ESTADO_COLA="estado_cola";

    public static final String ID_NIVELTURNO="id_nivelturno";
    public static final String TIPO="tipo";
    public static final String EXPLANATION_SCHEDULE = "schedule_explanation";



    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public boolean isLogin()  {
        return preferences.getBoolean(IS_LOGIN, false);
    }


    public void openSession(AccessTokenEntity token) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(USER_TOKEN, token.getAccess_token());
        editor.commit();
    }


    public boolean isExplanationSchedule()  {
        return preferences.getBoolean(EXPLANATION_SCHEDULE, false);
    }

    public void setExplanationSchedle(boolean active){
        editor.putBoolean(EXPLANATION_SCHEDULE, active);
        editor.commit();
    }


    public void closeSession() {
        editor.putBoolean(IS_LOGIN, false);
        editor.putString(USER_TOKEN, null);
        editor.putString(USER_JSON, null);
        editor.commit();
    }


    public String getUserToken() {
        if (isLogin()) {
            return preferences.getString(USER_TOKEN, "");
        } else {
            return "";
        }
    }

    public void setUser(UserEntity userEntity) {
        if (userEntity != null) {
            Gson gson = new Gson();
            String user = gson.toJson(userEntity);
            editor.putString(USER_JSON, user);
        }
        editor.commit();
    }

    public UserEntity getUserEntity() {
        String userData = preferences.getString(USER_JSON, null);
        return new Gson().fromJson(userData, UserEntity.class);
    }

    public void setEmailUser(String email) {
        editor.putString(USER_MAIL, email);
        editor.commit();
    }

    public String getUserEmail() {
        return preferences.getString(USER_MAIL, "");
    }


    public void setIdComida(String idComida) {
        editor.putString(ID_COMIDA, idComida);
        editor.commit();
    }

    public String getIdComida() {
        return preferences.getString(ID_COMIDA, "");
    }

    public void setIdNivelTurno(String nivelTurno) {
        editor.putString(ID_NIVELTURNO, nivelTurno);
        editor.commit();
    }

    public String getIdNivelturno() {
        return preferences.getString(ID_NIVELTURNO, "");
    }

    public void setTipo(String tipo) {
        editor.putString(TIPO, tipo);
        editor.commit();
    }

    public String getTipo() {
        return preferences.getString(TIPO, "");
    }

    public void setTurno(String turno) {
        editor.putString(TURNO, turno);
        editor.commit();
    }

    public String getTurno() {
        return preferences.getString(TURNO, "");
    }


    public void setNivel(String nivel) {
        editor.putString(NIVEL, nivel);
        editor.commit();
    }

    public String getNivel() {
        return preferences.getString(NIVEL, "");
    }

    public void setEstadocola(int nivel) {
        editor.putInt(ESTADO_COLA, nivel);
        editor.commit();
    }

    public int getEstadoCola() {
        return preferences.getInt(ESTADO_COLA, 0);
    }

}
