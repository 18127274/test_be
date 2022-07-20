package DemoWebAPI.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LOAIPHEP")
public class LoaiPhep {
	@Id
	private long ID;
	private long MaLoai;
	private String TenLoai;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public long getMaLoai() {
		return MaLoai;
	}
	public void setMaLoai(long maLoai) {
		MaLoai = maLoai;
	}
	public String getTenLoai() {
		return TenLoai;
	}
	public void setTenLoai(String tenLoai) {
		TenLoai = tenLoai;
	}
	public LoaiPhep() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
