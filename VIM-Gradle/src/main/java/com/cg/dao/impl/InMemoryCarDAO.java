package com.cg.dao.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.cg.beans.CarDTO;
import com.cg.dao.CarDAO;

public class InMemoryCarDAO implements CarDAO {
	public InMemoryCarDAO() {
		System.out.println("InMemoryCarDAO  instantiated ...");
	}
	
	
//	private List<CarDTO> cars = new LinkedList<CarDTO>();
	private Map<Integer, CarDTO> carsEntries = new HashMap<Integer, CarDTO>();
	
	@Override
	public List<CarDTO> findAll() {
		// TODO Auto-generated method stub
		return new LinkedList(carsEntries.values());
	}

	@Override
	public CarDTO findById(int id) {
		// TODO Auto-generated method stub
		return carsEntries.get(id);
	}

	@Override
	public void create(CarDTO car) {
		// TODO Auto-generated method stub
//		cars.add(car);
//		car.assignId();
//		carsEntries.put(car.getIdOfACar(), car);
	}

	@Override
	public void update(CarDTO car) {
//		carsEntries.put(car.getIdOfACar(), car);
	}

	@Override
	public void delete(String[] ids) {
		System.out.println("deleting cars...");
		System.out.println(carsEntries);
		
		for(String id : ids){
			System.out.println(id);
			System.out.println(carsEntries.remove(Integer.parseInt(id)));
		}
		
		System.out.println(carsEntries.size());
	}

}
