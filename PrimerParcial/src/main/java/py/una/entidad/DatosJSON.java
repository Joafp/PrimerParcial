package py.una.entidad;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class DatosJSON {
    public static void main(String[] args) throws Exception {
    	DatosJSON representacion = new DatosJSON();
    	System.out.println("Ejemplo de uso 1: pasar de objeto a string");
    	Datos t = new Datos();
    	t.setid(1);
    	t.setciudad("Asuncion");
    	t.setporcentaje(15.5);
    	t.settemperatura(26.5);
    	t.setvelocidad(64);
    	t.setfecha("12/12/2022");
    	t.sethora("13:00");
    	String r1 = representacion.objetoString(t);
    	System.out.println(r1);
        Datos prueba=DatosJSON.stringObjeto(r1);
        System.out.println(prueba.id);
    }
    public static String objetoString(Datos p) {	
    	
		JSONObject obj = new JSONObject();
        obj.put("id", p.getid());
        obj.put("ciudad", p.getciudad());
        obj.put("porcentaje", p.getporcentaje());
        obj.put("temperatura", p.gettemperatura());
        obj.put("velocidad", p.getvelocidad());
        obj.put("fecha", p.getfecha());
        obj.put("hora", p.gethora());
        return obj.toJSONString();
    }
    public static Datos stringObjeto(String str) throws Exception {
    	Datos p = new Datos();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;
        p.setid((long)jsonObject.get("id"));
        p.setciudad((String)jsonObject.get("ciudad"));
        p.setporcentaje((Double)jsonObject.get("porcentaje"));
        p.settemperatura((Double)jsonObject.get("temperatura"));
        p.setvelocidad((Double)jsonObject.get("velocidad"));
        p.setfecha((String)jsonObject.get("fecha"));
        p.sethora((String)jsonObject.get("hora"));
        return p;
	}

}
