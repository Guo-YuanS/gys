package JsonToSql;

import java.io.InputStream;
import java.io.OutputStream;
/**
 * 
 * Author:guoy10
 */
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

public class ArrayObject implements Serializable{
	
	private String NAME;
	private String SPA_IP;
	private String SPB_IP;
	private String BMCA_IP;
	private String BMCB_IP;
	private String OWNER;
	private String DATE;
	private String MODE;
	
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String NAME) {
		this.NAME = NAME;
	}
	public String getSPA_IP() {
		return SPA_IP;
	}
	public void setSPA_IP(String SPA_IP) {
		this.SPA_IP = SPA_IP;
	}
	public String getSPB_IP() {
		return SPB_IP;
	}
	public void setSPB_IP(String SPB_IP) {
		this.SPB_IP = SPB_IP;
	}
	public String getBMCA_IP() {
		return BMCA_IP;
	}
	public void setBMCA_IP(String BMCA_IP) {
		this.BMCA_IP = BMCA_IP;
	}
	public String getBMCB_IP() {
		return BMCB_IP;
	}
	public void setBMCB_IP(String BMCB_IP) {
		this.BMCB_IP = BMCB_IP;
	}
	public String getOWNER() {
		return OWNER;
	}
	public void setOWNER(String OWNER) {
		this.OWNER = OWNER;
	}
	public String getDATE() {
		return DATE;
	}
	public void setDATE(String DATE) {
		this.DATE = DATE;
	}
	public String getMODE() {
		return MODE;
	}
	public void setMODE(String MODE) {
		this.MODE = MODE;
	}
	@Override
	public String toString() {
		return this.NAME+""+this.SPA_IP+""+this.SPB_IP+""+this.BMCA_IP+""+this.BMCB_IP+""+this.OWNER+""+this.DATE+""+this.MODE;
	}
	
}
