package DemoWebAPI.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "THAMGIADUAN")
public class ThamGiaDuAn {
	@Id
	private long ID;
	private long MaNV;
	private long MATGDA;
	private long MATL;
	private String VaiTro;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public long getMaNV() {
		return MaNV;
	}
	public void setMaNV(long maNV) {
		MaNV = maNV;
	}
	public long getMATGDA() {
		return MATGDA;
	}
	public void setMATGDA(long mATGDA) {
		MATGDA = mATGDA;
	}
	public long getMATL() {
		return MATL;
	}
	public void setMATL(long mATL) {
		MATL = mATL;
	}
	public String getVaiTro() {
		return VaiTro;
	}
	public void setVaiTro(String vaiTro) {
		VaiTro = vaiTro;
	}
	public ThamGiaDuAn() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
