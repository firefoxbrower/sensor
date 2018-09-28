package com.sensor.entities;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("opo_user")
public class opo_user implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @Comment("ID")
    private Long id;

    @Column
    @Comment("关联系统用户ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String uuid;

    @Column
    @Comment("二级密码")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String safe_password;// transient 修饰符可让此字段不在对象里显示

    @Column
    @Comment("真实姓名")
    private String real_name;

    @Column
    @Comment("身份证")
    private String Id_card;

    @Column
    @Comment("手机号")
    private String phone;

    @Column
    @Comment("密保问题")
    private String safe_question;

    @Column
    @Comment("密保答案")
    private String safe_solution;

    @Column
    @Comment("银行账号姓名")
    private String bank_account_name;

    @Column
    @Comment("银行开户行")
    private String bank;

    @Column
    @Comment("开户行详细资料")
    private String bank_branch;

    @Column
    @Comment("银行账号")
    private String bank_account;

    @Column
    @Comment("支付宝账号")
    private String alipay;

    @Column
    @Comment("微信")
    private String we_chat;

    @Column
    @Comment("邀请人")
    private String inviter;

    @Column
    @Comment("领导人电话号码")
    private String inviter_phone;

/*    @One(target = Sys_user.class, field = "id",key ="id")
    public Sys_user user;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSafe_password() {
        return safe_password;
    }

    public void setSafe_password(String safe_password) {
        this.safe_password = safe_password;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getId_card() {
        return Id_card;
    }

    public void setId_card(String id_card) {
        Id_card = id_card;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSafe_question() {
        return safe_question;
    }

    public void setSafe_question(String safe_question) {
        this.safe_question = safe_question;
    }

    public String getSafe_solution() {
        return safe_solution;
    }

    public void setSafe_solution(String safe_solution) {
        this.safe_solution = safe_solution;
    }

    public String getBank_account_name() {
        return bank_account_name;
    }

    public void setBank_account_name(String bank_account_name) {
        this.bank_account_name = bank_account_name;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBank_branch() {
        return bank_branch;
    }

    public void setBank_branch(String bank_branch) {
        this.bank_branch = bank_branch;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getWe_chat() {
        return we_chat;
    }

    public void setWe_chat(String we_chat) {
        this.we_chat = we_chat;
    }

    public String getInviter() {
        return inviter;
    }

    public void setInviter(String inviter) {
        this.inviter = inviter;
    }

    public String getInviter_phone() {
        return inviter_phone;
    }

    public void setInviter_phone(String inviter_phone) {
        this.inviter_phone = inviter_phone;
    }

/*    public Sys_user getUser() {
        return user;
    }

    public void setUser(Sys_user user) {
        this.user = user;
    }*/

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }
}