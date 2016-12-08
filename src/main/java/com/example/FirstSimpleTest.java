package com.example;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by solg on 07.12.2016.
 */
public class FirstSimpleTest {

 @Test
 public void simpleTest() {
  int res = 8;
  int sum = 3 + 5;

  Assert.assertEquals(sum, res);
//     Assert.assertTrue();
//     Assert.assertFalse();
 }

 @Test
 public void simpleFalseTest() {
  int res = 8;
  int sum = 1 + 5;

  Assert.assertEquals(sum, res, "sum not equals");
 }

 @Test
 public static void simpleAsertTrue(){
  Assert.assertTrue(5>2);
 }

 @Test
 public static void simpleAsertNotTrue(){
  Assert.assertTrue(5<2, "5 < 2 not true");
 }

 @Test

 public static void simpleAsertFalse(){
  Assert.assertFalse(5<2);
 }
}
