package DemoWebAPI.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.annotation.PostConstruct;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@Document(collection = "WFH")

public class WFH {
	
	//@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Id
	private long ID;
	private long MaNguoiDuyet;
	private long MaNhanVien;
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private String NgayBatDau;
	private String NgayKetThuc;
	private String LyDo;
	private String LyDoTuChoi;
	private String TrangThai;
	
	
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public long getMaNguoiDuyet() {
		return MaNguoiDuyet;
	}
	public void setMaNguoiDuyet(long maNguoiDuyet) {
		MaNguoiDuyet = maNguoiDuyet;
	}
	public long getMaNhanVien() {
		return MaNhanVien;
	}
	public void setMaNhanVien(long maNhanVien) {
		MaNhanVien = maNhanVien;
	}
	
	public String getNgayBatDau() {
		return NgayBatDau;
	}
	public void setNgayBatDau(String ngayBatDau) {
		NgayBatDau = ngayBatDau;
	}
	public String getNgayKetThuc() {
		return NgayKetThuc;
	}
	public void setNgayKetThuc(String ngayKetThuc) {
		NgayKetThuc = ngayKetThuc;
	}
	public String getLyDo() {
		return LyDo;
	}
	public void setLyDo(String lyDo) {
		LyDo = lyDo;
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
	
	
	
	public WFH(long iD, long maNguoiDuyet, long maNhanVien, String ngayBatDau, String ngayKetThuc, String lyDo,
			String lyDoTuChoi, String trangThai) {
		super();
		ID = iD;
		MaNguoiDuyet = maNguoiDuyet;
		MaNhanVien = maNhanVien;
		NgayBatDau = ngayBatDau;
		NgayKetThuc = ngayKetThuc;
		LyDo = lyDo;
		LyDoTuChoi = lyDoTuChoi;
		TrangThai = trangThai;
	}
	public WFH() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
