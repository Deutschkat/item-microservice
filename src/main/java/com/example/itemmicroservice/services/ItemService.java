package com.example.itemmicroservice.services;

import com.example.itemmicroservice.models.Item;
import com.example.itemmicroservice.repos.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public Item getItemById(Long id) {
        Optional<Item> optional;
        if ((optional = itemRepository.findById(id)).isEmpty()) {
            return null;
        } else {
            return optional.get();
        }

    }

    public Item getItemByName(String name) {
        return itemRepository.findByName(name);
    }

    //Possible Problem Here
    public Item insertNewItem(Item newItem) {
        return itemRepository.save(newItem);
    }

    //and here too
    public Item updateItem(Item updatedItem) {
        Optional<Item> existingItemOptional = itemRepository.findById(updatedItem.getItemId());
        if (existingItemOptional.isPresent()) {
            Item item = existingItemOptional.get();
            BeanUtils.copyProperties(updatedItem, item, "itemId");
            return itemRepository.save(item);
        }
        return null;
    }

    public void deleteItemById(Long id){
        itemRepository.deleteById(id);
    }


    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
