package com.medical.Shop;

import java.util.ArrayList;
import java.util.List;

import com.medical.Pharmacy.Order;
import com.medical.Pharmacy.Pharmacy;


public class OrderOperations extends ShopOperations {
	
	/**
	 * Place order of required medicine in your store from warehouse or distributer
	 */
	public void placeOrder() {
		Order order = new Order();
		showAllMedicine();
		System.out.println("Enter Medicine_Id to order medicine");
		int id = sc.nextInt();
		sc.nextLine();
		Pharmacy pharmacy = list.get(--id);
		order.setMedicine_id(pharmacy.getId());
		System.out.println("Enter Quantity of medicine " + pharmacy.getMedicine().getName() + " to place order");
		int quantity = sc.nextInt();
		order.setQuantity(quantity);
		order.setStatus("Not received");
		placedOrders.add(order);
		System.out.println("Order placed " + order);
	}
	
	
	/**
	 * Place order of parameterized medicine in your store from warehouse or distributer
	 * @param pharmacy Object of Pharmacy class which contains medicine details.
	 * @param quantity Integer value of medicine required.
	 * @return Object of order class.
	 */
	public static Order placeOrder(Pharmacy pharmacy,int quantity) {
		Order order = new Order();
		order.setMedicine_id(pharmacy.getId());
		order.setQuantity(quantity);
		order.setStatus("Not Complete");
		placedOrders.add(order);
		return order;
	}

	/**
	 * Change the status of order when order get delivered and adds medicine in stock
	 */
	public void deliveryReport() {
		System.out.println("Enter order id to change delivery status");
		showAllOrder();
		int id = sc.nextInt();
		Order order = placedOrders.get(id - 1);
		Pharmacy pharmacy = list.get(order.getMedicine_id() - 1);
		pharmacy.setQuantity(pharmacy.getQuantity() + order.getQuantity());
		order.setStatus("Delivered");

	}

	/**
	 * Show All Oreders in store
	 */
	public void showAllOrder() {
		showOrders(placedOrders);
	}

	/**
	 * Shows all orders which are not delivered at store 
	 */
	public void showPendingOrders() {
		List<Order> orderList=new ArrayList<Order>();
		for(Order order:placedOrders) {
			if(!order.getStatus().equals("Delivered")){
				orderList.add(order);
			}
		}
		showOrders(orderList);
	}

	/**
	 * prints the parameterized  list of orders
	 * @param orderList list of orders
	 */
	public void showOrders(List<Order> orderList) {
		int count = 1;
		System.out.println("Order_Id        Medicine_Name       Quantity      Status");
		for (Order order : orderList) {
			System.out.println(count++ + "    " + (list.get(order.getMedicine_id() - 1)).getMedicine().getName()
					+ "     " + order.getQuantity() + "    " + order.getStatus());
		}
	}

}
