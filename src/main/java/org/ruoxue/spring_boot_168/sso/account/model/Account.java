package org.ruoxue.spring_boot_168.sso.account.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sso_account", indexes = { @Index(name = "UK_sso_account_cid", columnList = "cid", unique = true),
		@Index(name = "IDX_sso_account_name", columnList = "name", unique = true) })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Account {
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(length = 32)
	@Id
	private String id;
	
	/** 帳號 */
	@Column(length = 30)
	private String cid;
	/** 名稱 */
	@Column(length = 50)
	private String name;
	/** 密码 */
	@Column(length = 32)
	private String password;
	/** 密码鹽 */
	@Column(length = 32)
	private String salt;

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.JSON_STYLE);
		builder.appendSuper(super.toString());
		builder.append("cid", cid);
		builder.append("name", name);
		return builder.toString();
	}
}
