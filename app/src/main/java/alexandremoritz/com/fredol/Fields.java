package alexandremoritz.com.fredol;

/**
 * Created by morit on 27/11/2017.
 */


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {


    public Fields(String nomEtablissement,String adresse ){
        this.nomEtablissement=nomEtablissement;
        this.adresse=adresse;

    }

    @SerializedName("ecrans")
    @Expose
    private Integer ecrans;
    @SerializedName("fauteuils")
    @Expose
    private String fauteuils;
    @SerializedName("ndegauto")
    @Expose
    private Integer ndegauto;
    @SerializedName("arrondissement")
    @Expose
    private Integer arrondissement;
    @SerializedName("art_et_essai")
    @Expose
    private String artEtEssai;
    @SerializedName("adresse")
    @Expose
    private String adresse;
    @SerializedName("nom_etablissement")
    @Expose
    private String nomEtablissement;
    @SerializedName("coordonnees")
    @Expose
    private List<Double> coordonnees = null;

    public Integer getEcrans() {
        return ecrans;
    }

    public void setEcrans(Integer ecrans) {
        this.ecrans = ecrans;
    }

    public String getFauteuils() {
        return fauteuils;
    }

    public void setFauteuils(String fauteuils) {
        this.fauteuils = fauteuils;
    }

    public Integer getNdegauto() {
        return ndegauto;
    }

    public void setNdegauto(Integer ndegauto) {
        this.ndegauto = ndegauto;
    }

    public Integer getArrondissement() {
        return arrondissement;
    }

    public void setArrondissement(Integer arrondissement) {
        this.arrondissement = arrondissement;
    }

    public String getArtEtEssai() {
        return artEtEssai;
    }

    public void setArtEtEssai(String artEtEssai) {
        this.artEtEssai = artEtEssai;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNomEtablissement() {
        return nomEtablissement;
    }

    public void setNomEtablissement(String nomEtablissement) {
        this.nomEtablissement = nomEtablissement;
    }

    public List<Double> getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(List<Double> coordonnees) {
        this.coordonnees = coordonnees;
    }

}
