package com.chinaopensource.apiserver.blog.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinaopensource.apiserver.ApiServerApplicationTests;
import com.chinaopensource.apiserver.blog.data.BlogPage;
import com.chinaopensource.apiserver.blog.data.SaveBlog;
import com.chinaopensource.apiserver.blog.service.BlogService;
import com.chinaopensource.apiserver.common.util.Base64Util;

public class BlogServiceImplTest extends ApiServerApplicationTests{

	@Autowired
	private BlogService blogService;
	
	@Test
	public void test() {
		String content = "PHAgPuaYqOWkqee+juWwkeWls+iJs+WzsOW+ruaIke+8jOWQkeaIkeaOqOiNkOS6huS4gOmDqOaXpeacrOW9seeJh+OAijxlbT7lv6Dniqzlhavlhaw8L2VtPuOAi++8jOW5tuWRiuivieaIkeimgeWkmuWkh+e6uOW3vuOAgjwvcD4KPHAgPuingueci+acn+mXtO+8jOazquawtOS4jeatouS4gOasoeaooeeziuS6huWPjOecvOOAgjwvcD4KPHAgPumYv+WFq+aYr+aVmeaOiOeahOS4gOadoeeLl++8jOS9huWcqOaVmeaOiOecvOmHjO+8jOWug+aYr+mdnuW4uOmHjeimgeeahOS4gOWQjeWutuW6reaIkOWRmO+8jOS7lue7meS6iOWug+eyvuW/g+eahOWRteaKpOWSjOeWvOeIseOAguaVmeaOiOabvuWvueS7lueahOWkquWkquivtO+8muKAnOS6uuacieadg+WIqe+8jOeLl+S5n+acieOAguKAneS7luS4jeiuuOS7huS6uuaIj+W8hOWug++8jOasuui0n+Wug+OAgjwvcD4KPHAgPuavj+WkqeaXqeS4iu+8jOWug+mZquS7luWOu+i9puerme+8m+avj+WkqeS4i+WNiO+8jOWug+WPiOWOu+i9puermeetieWAmeaVmeaOiOS4i+ePreWbnuWutuOAguacieS4gOWkqe+8jOaVmeaOiOeMneatu+WcqOWkp+WtpuiusuWPsOS5i+S4iu+8jOWGjeS5n+ayoeacieiDveWHuueOsOWcqOi9puermeeahOWHuuWPo+WkhOKApuKApjwvcD4KPHAgPuWug+aYr+S7lueahOWkqeS9v++8jOS7luaYr+Wug+eahOWuiOWAmeOAgjwvcD4KPHAgPuaYvOWknOS6pOabv++8jOWbm+Wto+i9ruWbnuS4re+8jOS4gOebtOS4jeabvuaUueWPmOeahOaYr+mYv+WFq+S4k+aDheeahOWuiOWAme+8jOebtOWIsOeUn+WRveeahOacgOWQjuS4gOWIu++8jOWug+S7v+S9m+WPiOeci+ingeaVmeaOiOKApuKApjwvcD4KPHAgPuS6uuS4juWFtuS7luWKqOeJqeS5i+mXtOeahOWMuuWIq+WcqOS6juS6uuacieW+iOW8uueahOaAnee7tOiDveWKm++8jOS5n+WPr+S7peivtOaYr+S6uueahOaZuuWVhui/nOmrmOS6juWFtuS7luWKqOeJqeOAgjwvcD4KPHAgPuS9huaYr+aIkeatpOaXtuinieW+l++8jOS6uuS4jueLl+eahOS5i+mXtOacgOWkp+eahOWMuuWIq+WcqOS6ju+8muW+iOWkmuaXtuWAme+8jOS6uui/nOS4jeWmgueLl+W/oOivmuWSjOefpeaBqeWbvuaKpeOAgjwvcD4KPHAgPuavj+S4quS6uueUn+WRveS4remDveS8muacieS4gOS4quaVmeaOiO+8jOS5n+aEv+avj+S4quS6uueUn+WRveS4remDveacieS4gOWPqueni+eUsOeLl+OAgjwvcD4KPHAgPuWtpuS8muefpeaBqe+8jOW/g+aAgOaEn+a/gOS7peWvuemCo+S6m+WWhOW+heaIkeS7rOeahOS6uuWQp++8mzwvcD4KPHAgPuWtpuS8muW/oOivmu+8jOa7oeiFuea3seaDheS7peWvuemCo+S6m+eWvOeIseaIkeS7rOeahOS6uuWQp+OAgjwvcD4KPHAgPuaIkeS7rOS4jeaYr+eLl++8jOS9huaYr+S4gOWumuS4jeimgea0u+eahOS4jeWmguS4gOadoeeLl++8gTwvcD4KPGJsb2NrcXVvdGUgPuS9nOiAhe+8mueyvuW9qeS6uueUn+i3rzxicj4K6ZO+5o6l77yaPGEgaHJlZj0iaHR0cHM6Ly93d3cuamlhbnNodS5jb20vcC83ZDc3NDFiMDE5ZTMiPmh0dHBzOi8vd3d3LmppYW5zaHUuY29tL3AvN2Q3NzQxYjAxOWUzPC9hPjwvYmxvY2txdW90ZT4KPHAgPiZuYnNwO+S+hua6kO+8mueugOS5piDokZfkvZzmnYPlvZLkvZzogIXmiYDmnInjgILllYbkuJrovazovb3or7fogZTns7vkvZzogIXojrflvpfmjojmnYPvvIzpnZ7llYbkuJrovazovb3or7fms6jmmI7lh7rlpITjgII8L3A+";
		SaveBlog sb = new SaveBlog();
		sb.setContent(content);
		sb.setCreateUser(1);
		sb.setStatus(0);
		sb.setTags("1");
		sb.setTitle("test");
		Base64Util.DecoderContent(sb);
		System.out.println(sb.getContent());
	 	blogService.saveBlog(sb);

	}
	
	@Test
	public void test1() {
		BlogPage bp = blogService.findBlogByUuidVersion("2661f36e-c0ac-4ab2-8833-0ed66389d3e1", 1);
		System.out.println(bp.getBlog().getContent());
		Base64Util.EncoderContent(bp.getBlog());
		System.out.println(bp.getBlog().getContent());
	}
}
