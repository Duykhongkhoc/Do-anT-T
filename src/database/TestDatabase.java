/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

public class TestDatabase {
	public TestDatabase() {
		Connect newConnect = new Connect();
		newConnect.truy_Xuat_CSDL();
	}
	public static void main(String[] args) {
		TestDatabase testDatabase =new TestDatabase();
	}
}