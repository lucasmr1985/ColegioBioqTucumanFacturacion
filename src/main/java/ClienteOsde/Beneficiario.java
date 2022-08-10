
package ClienteOsde;

/**
 *
 * @author Lucas Robles
 */
public class Beneficiario {
    private String ApellidoBeneficiario;
    private String NombreBeneficiario;
    private String Sexo;
    private String FechaNacimiento;

    public Beneficiario() {
    }

    public String getApellidoBeneficiario() {
        return ApellidoBeneficiario;
    }

    public void setApellidoBeneficiario(String ApellidoBeneficiario) {
        this.ApellidoBeneficiario = ApellidoBeneficiario;
    }

    public String getNombreBeneficiario() {
        return NombreBeneficiario;
    }

    public void setNombreBeneficiario(String NombreBeneficiario) {
        this.NombreBeneficiario = NombreBeneficiario;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }
   
}
