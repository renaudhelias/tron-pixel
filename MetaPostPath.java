import org.apache.batik.anim.dom.SVGOMPathElement;
import org.apache.batik.dom.svg.AbstractSVGPathSegList.SVGPathSegCurvetoCubicItem;
import org.apache.batik.dom.svg.AbstractSVGPathSegList.SVGPathSegMovetoLinetoItem;
import org.apache.batik.dom.svg.SVGItem;
import org.apache.batik.dom.svg.SVGPathSegItem;
import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGPathSegList;

public class MetaPostPath {

	public MetaPostPath(int line, Node node) {
		//System.out.println(node.getClass().getName());
		SVGOMPathElement pathElement = (SVGOMPathElement)node;
		
		SVGPathSegList pathList = pathElement.getNormalizedPathSegList();

	    int pathObjects = pathList.getNumberOfItems();

	    boolean moveDone=false;
	    boolean first=true;
	    
	    int c=0;
	    int line2=line;
    	System.out.print(""+line2+" ");

	    for( int i = 0; i < pathObjects; i++ ) {
	    	c++;
	    	if (c==10) {
	    		c=0;
	    		System.out.println("");
	    		line2++;
	    		System.out.print(""+line2+" ");
	    		first=true;
	    	}
	    	if (!first) {
	    		System.out.print(":");
	    	}
	    	first=false;
	      SVGItem item = (SVGItem)pathList.getItem( i );
	      if (item instanceof SVGPathSegMovetoLinetoItem) {
	    	  SVGPathSegMovetoLinetoItem moveTo = (SVGPathSegMovetoLinetoItem)item;
	    	  if (moveTo.getPathSegType()==2) {
	    		  System.out.print("MOVE "+transX(moveTo.getX())+","+transY(moveTo.getY()));
	    	  } else {
	    		  System.out.print("DRAW "+transX(moveTo.getX())+","+transY(moveTo.getY()));
	    	  }
	    	  moveDone=true;
	      } else if (item instanceof SVGPathSegCurvetoCubicItem) {
	    	  SVGPathSegCurvetoCubicItem segCurve=(SVGPathSegCurvetoCubicItem) item;
	    	  System.out.print("DRAW "+transX(segCurve.getX())+","+transY(segCurve.getY()));
	      } else if (item instanceof SVGPathSegItem) {
	    	  SVGPathSegItem pSeg = (SVGPathSegItem)item;
//	    	  System.out.print("MOVE("+	pSeg.getValueAsString()+") "+transX(pSeg.getX())+","+transY(pSeg.getY())+":DRAW "+transX(pSeg.getX2())+","+transY(pSeg.getY2()));
	      } else {
	    	  System.out.println(item.getClass().getName());
	      }
	      
	      
//	      System.out.println(String.format( "%s%n", item.getValueAsString() ) );
	    }
	      System.out.println("");
	}

	private float transY(float y) {
		return Math.round(400-y*0.7f-90);
	}

	private float transX(float x) {
		return Math.round(x*0.7f+145);
	}

//	public String toCode() {
//		return "";
//	}

}
