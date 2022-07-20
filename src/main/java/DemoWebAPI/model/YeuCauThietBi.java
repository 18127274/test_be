package DemoWebAPI.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "YEUCAUTHIETBI")

public class YeuCauThietBi {
	@Id
	private long ID;
	private long MaYeuCau;
	private String MoTa;
	private float SoLuong;
	private float ChiPhi;
	private String LyDoTuChoi;
	private String TrangThai;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public long getMaYeuCau() {
		return MaYeuCau;
	}
	public void setMaYeuCau(long maYeuCau) {
		MaYeuCau = maYeuCau;
	}
	public String getMoTa() {
		return MoTa;
	}
	public void setMoTa(String moTa) {
		MoTa = moTa;
	}
	public float getSoLuong() {
		return SoLuong;
	}
	public void setSoLuong(float soLuong) {
		SoLuong = soLuong;
	}
	public float getChiPhi() {
		return ChiPhi;
	}
	public void setChiPhi(float chiPhi) {
		ChiPhi = chiPhi;
	}
	public String getLyDoTuChoi() {
		return LyDoTuChoi;
	}
	public void setLyDoTuChoi(String lyDoTuChoi) {
		LyDoTuChoi = lyDoTuChoi;
	}
	public String getTrangThai() {
		return TrangThai;
	}
	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}
	public YeuCauThietBi() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
