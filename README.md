# Highlight


## 用法


#### 1. 使用案例

	private HighLight mHighLight;
    
    private void showNormalTipsAll(View view) {
        mHighLight = new HighLight(MainActivity.this);

        SingleViewInfo leftViewInfo = mHighLight.attachViewInfo(R.id.btn_rightLight, LightShape.create(LightShape.RECT), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.LEFT));
        SingleViewInfo rightViewInfo = mHighLight.attachViewInfo(R.id.btn_light, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.RIGHT));
        SingleViewInfo topViewInfo = mHighLight.attachViewInfo(R.id.btn_bottomLight, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.TOP));
        SingleViewInfo bottomViewInfo = mHighLight.attachViewInfo(view, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.BOTTOM));

        mHighLight.show(leftViewInfo, rightViewInfo, topViewInfo, bottomViewInfo);
        mHighLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHighLight.remove();
            }
        });
    }


attachViewInfo包含3个参数：

* 参数1：需要高亮view的id
* 参数2：高亮部分将要被绘制的形状，目前默认支持{圆形、矩形、椭圆}；当然允许自定义；允许为空，默认为矩形
* 参数2：你的tip布局，也就是箭头和文字等等信息，可以自己编写个布局，参考demo即可。
* 参数3：tip布局所在的位置信息，目前默认支持{正下方、正上方、正左方、正右方}；当然允许自定义；允许为空，默认为正下方

## 相比较原先HighLight修改处
* 1，实现类中，没有使用Bitmap，彻底解决OOM问题
* 2，每次展示的顺序和个数，由使用者自定义；可以搭配出更多效果，详情参考demo
* 3，解决原先的点击事件穿透问题
* 4，简化原先的实现逻辑，去除所有冗余的回调


## Question

1. 如何自定义高亮部分会绘制的形状
	> 1, 参考默认实现，实现一套自己的类；参考类：`LightShapeImpl.Bottom`
	> 
	> 2，在创建`SingleViewInfo`传入你自定义的类
	
2. 如何自定义tip布局在屏幕中的位置

	> 1, 参考默认实现，实现一套自己的类；参考类：`HintMarginImpl.Rect`
	> 
	> 2, 在创建`SingleViewInfo`传入你自定义的类
	
3. 怎么针对同一个高亮控件添加多个提示布局
	> 调用`hightLight.show`是添加多个`SingleViewInfo`，只需要高亮的view相同即可

