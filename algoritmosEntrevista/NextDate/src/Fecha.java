
public class Fecha {
	
	private int dia;
	private int mes;
	private int a�o;
	
	public Fecha(){
		
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		
		if((dia <= 1) && (dia <= 31)){
			if(mes == 2){
				if(dia <= 28){
					this.dia= dia;
				}System.out.println("numero de dias no coresponden con un fehca correcta");
			}
			if(((mes% 2 != 0) && (mes < 7))){
				this.dia= dia;
			}
			else if((dia > 7) && (mes % 2 == 0)){
				this.dia = dia;
			}System.out.println("numero de dias no coresponden con un fehca correcta");
		
		}System.out.println("numero de dias no coresponden con un fehca correcta");
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		if((mes >= 1) && (mes <= 12)){
			this.mes= mes;
		}System.out.println("numero de mes no coresponde con un fehca correcta");
	}
	public int getA�o() {
		return a�o;
	}
	public void setA�o(int a�o) {
		if(a�o > 0){
			this.a�o= a�o;
		}System.out.println("numero de a�os no coresponden con un fehca correcta");
	}
	public String getFecha(){
		return dia + "/" + mes + "/" + a�o+"";
	}
	public void agregardia(){
		setDia(getDia() +1);
	}
}
