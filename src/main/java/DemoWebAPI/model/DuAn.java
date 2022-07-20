package DemoWebAPI.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DUAN")
public class DuAn {
	@Id
	private long ID;
	private long MaDuAn;
	private long MaPM;
	private String MoTa;
	private String TenDuAn;
	private LocalDate NgayBatDau;
	private LocalDate NgayKetThuc;
	private String TrangThai;
	
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public long getMaDuAn() {
		return MaDuAn;
	}

	public void setMaDuAn(long maDuAn) {
		MaDuAn = maDuAn;
	}

	public long getMaPM() {
		return MaPM;
	}

	public void setMaPM(long maPM) {
		MaPM = maPM;
	}

	public String getMoTa() {
		return MoTa;
	}

	public void setMoTa(String moTa) {
		MoTa = moTa;
	}

	public String getTenDuAn() {
		return TenDuAn;
	}

	public void setTenDuAn(String tenDuAn) {
		TenDuAn = tenDuAn;
	}

	public LocalDate getNgayBatDau() {
		return NgayBatDau;
	}

	public void setNgayBatDau(LocalDate ngayBatDau) {
		NgayBatDau = ngayBatDau;
	}

	public LocalDate getNgayKetThuc() {
		return NgayKetThuc;
	}

	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		NgayKetThuc = ngayKetThuc;
	}

	public String getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}

	public DuAn() {
		super();
		// TODO Auto-generated constructor stub
	}

}
