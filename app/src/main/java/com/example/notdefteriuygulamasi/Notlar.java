package com.example.notdefteriuygulamasi;

import android.os.Parcel;
import android.os.Parcelable;

public class Notlar implements Parcelable {
    private String notAciklama;
    private String notBaslik;
    private String notTarih;
    private String notID;

    public Notlar() {
    }

    public Notlar(String notAciklama, String notBaslik, String notTarih,String notID) {
        this.notAciklama = notAciklama;
        this.notBaslik = notBaslik;
        this.notTarih = notTarih;
        this.notID=notID;
    }

    public String getNotID() {
        return notID;
    }

    public void setNotID(String notID) {
        this.notID = notID;
    }

    public String getNotAciklama() {
        return notAciklama;
    }

    public void setNotAciklama(String notAciklama) {
        this.notAciklama = notAciklama;
    }

    public String getNotBaslik() {
        return notBaslik;
    }

    public void setNotBaslik(String notBaslik) {
        this.notBaslik = notBaslik;
    }

    public String getNotTarih() {
        return notTarih;
    }

    public void setNotTarih(String notTarih) {
        this.notTarih = notTarih;
    }

    protected Notlar(Parcel in) {
        notAciklama=in.readString();
        notBaslik=in.readString();
        notTarih=in.readString();
    }

    public static final Creator<Notlar> CREATOR = new Creator<Notlar>() {
        @Override
        public Notlar createFromParcel(Parcel in) {
            return new Notlar(in);
        }

        @Override
        public Notlar[] newArray(int size) {
            return new Notlar[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(notAciklama);
        parcel.writeString(notBaslik);
        parcel.writeString(notTarih);
    }
}
