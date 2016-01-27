package polytech.pfe_ndar.objdisplaytools.min3d.vos;

import polytech.pfe_ndar.objdisplaytools.min3d.interfaces.IDirtyManaged;
import polytech.pfe_ndar.objdisplaytools.min3d.interfaces.IDirtyParent;


public abstract class AbstractDirtyManaged implements IDirtyManaged
{
	protected IDirtyParent _parent;
	protected boolean _dirty;
	
	public AbstractDirtyManaged(IDirtyParent $parent)
	{
		_parent = $parent;
	}
	
	public boolean isDirty()
	{
		return _dirty;
	}
	
	public void setDirtyFlag()
	{
		_dirty = true;
		if (_parent != null) _parent.onDirty();
	}
	
	public void clearDirtyFlag()
	{
		_dirty = false;
	}
}
