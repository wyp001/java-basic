package com.wyp.basic.genericity;

public class GlmapperGeneric<T> {
  private T t;
    public void set(T t) { this.t = t; }
    public T get() { return t; }
  
    public static void main(String[] args) {
        // do nothing

    }

  /**
    * 不指定类型
    */
  public void noSpecifyType(){
    GlmapperGeneric glmapperGeneric = new GlmapperGeneric();
    glmapperGeneric.set("test");
    // 需要强制类型转换
    String test = (String) glmapperGeneric.get();
    System.out.println(test);
  }

  /**
    * 指定类型
    */
  public void specifyType(){
    GlmapperGeneric<String> glmapperGeneric = new GlmapperGeneric();
    glmapperGeneric.set("test");
    // 不需要强制类型转换
    String test = glmapperGeneric.get();
    System.out.println(test);
  }
}