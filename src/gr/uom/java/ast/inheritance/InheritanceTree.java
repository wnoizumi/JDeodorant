package gr.uom.java.ast.inheritance;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

public class InheritanceTree {
    private DefaultMutableTreeNode rootNode;
    private volatile int hashCode = 0;

    public InheritanceTree() {
        this.rootNode = null;
    }

    public InheritanceTree(String nodeName) {
        this.rootNode = new DefaultMutableTreeNode(nodeName);
    }

    public DefaultMutableTreeNode getRootNode() {
        return rootNode;
    }

    public boolean contains(String nodeName) {
    	Enumeration<DefaultMutableTreeNode> e = rootNode.breadthFirstEnumeration();
        while(e.hasMoreElements()) {
            DefaultMutableTreeNode node = e.nextElement();
            if(node.getUserObject().equals(nodeName)) {
            	return true;
            }
        }
        return false;
    }

    public DefaultMutableTreeNode getNode(String nodeName) {
        if(rootNode != null) {
            Enumeration<DefaultMutableTreeNode> e = rootNode.breadthFirstEnumeration();
            while(e.hasMoreElements()) {
                DefaultMutableTreeNode node = e.nextElement();
                if(node.getUserObject().equals(nodeName)) {
                    return node;
                }
            }
        }
        return null;
    }

    public void addChildToParent(String childNode, String parentNode) {
        DefaultMutableTreeNode cNode = getNode(childNode);
        if(cNode == null) {
            cNode = new DefaultMutableTreeNode(childNode);
        }
        DefaultMutableTreeNode pNode = getNode(parentNode);
        if(pNode == null) {
            pNode = new DefaultMutableTreeNode(parentNode);
            rootNode = pNode;
        }
        pNode.add(cNode);
    }

    public void addChildRootNodeToParent(DefaultMutableTreeNode childRootNode, String parentNode) {
		DefaultMutableTreeNode pNode = getNode(parentNode);
		if(pNode == null) {
			pNode = new DefaultMutableTreeNode(parentNode);
			rootNode = pNode;
		}
		pNode.add(childRootNode);
	}

    public Set<String> getLeaves() {
    	Set<String> leaves = new LinkedHashSet<String>();
    	Enumeration<DefaultMutableTreeNode> e = rootNode.breadthFirstEnumeration();
    	while(e.hasMoreElements()) {
            DefaultMutableTreeNode node = e.nextElement();
            if(node.isLeaf()) {
            	leaves.add((String) node.getUserObject());
            }
    	}
    	return leaves;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o instanceof InheritanceTree) {
            InheritanceTree inheritanceTree = (InheritanceTree)o;
            rootNode.getUserObject().equals(inheritanceTree.rootNode.getUserObject());
        }
        return false;
    }

    public int hashCode() {
		if(hashCode == 0) {
    		int result = 17;
    		result = 37*result + rootNode.getUserObject().hashCode();
    		hashCode = result;
    	}
    	return hashCode;
	}
}
