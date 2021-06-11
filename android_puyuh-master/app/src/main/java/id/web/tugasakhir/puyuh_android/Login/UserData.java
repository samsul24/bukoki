package id.web.tugasakhir.puyuh_android.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("ada")
    @Expose
    private Boolean ada;

    @SerializedName("id")
    @Expose
    private String idUser;

    @SerializedName("name")
    @Expose
    private String nameUser;

    @SerializedName("status")
    @Expose
    private String statusUser;

    public UserData(Boolean ada, String idUser, String nameUser, String statusUser) {
        this.ada = ada;
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.statusUser = statusUser;
    }

    public Boolean getAda() {
        return ada;
    }

    public void Boolean(Boolean ada) {
        this.ada = ada;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(String statusUser) {
        this.statusUser = statusUser;
    }
}
