package fr.paris.lutece.portal.service.resource;

import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
 * Class to notify listeners of actions performed on resources. Listeners keep
 * at least track of the number of actions performed over a given resource
 */
public class ExtendableResourceActionHit
{
    private static ExtendableResourceActionHit _instance;

    /**
     * Private constructor
     */
    private ExtendableResourceActionHit( )
    {
    }

    /**
     * Get the service instance
     * @return The instance of the service
     */
    public static ExtendableResourceActionHit getInstance( )
    {
        if ( _instance == null )
        {
            synchronized ( ExtendableResourceActionHit.class )
            {
                _instance = new ExtendableResourceActionHit( );
            }
        }
        return _instance;
    }

    /**
     * Get the total number of hit associated to a given action name and
     * resource type
     * @param strActionName The name of the action to get the number of hit of
     * @param strExtendableResourceType The resource type to get the hit of
     * @return The number of hit, or 0 if this action has no hit for this
     *         resource type
     */
    public int getActionHit( String strActionName, String strExtendableResourceType )
    {
        int nResult = 0;
        for ( IExtendableResourceActionHitListener listener : SpringContextService
                .getBeansOfType( IExtendableResourceActionHitListener.class ) )
        {
            nResult += listener.getActionHit( strActionName, strExtendableResourceType );
        }
        return nResult;
    }

    /**
     * Get the list of action names associated with a number of hit for a given
     * resource
     * @param strExtendableResourceId The id of the resource
     * @param strExtendableResourceType The type of the resource
     * @return A map containing associations between action names and hit number
     */
    public Map<String, Integer> getResourceHit( String strExtendableResourceId, String strExtendableResourceType )
    {
        Map<String, Integer> mapActionHit = new HashMap<String, Integer>( );
        for ( IExtendableResourceActionHitListener listener : SpringContextService
                .getBeansOfType( IExtendableResourceActionHitListener.class ) )
        {
            for ( Entry<String, Integer> entry : listener.getResourceHit( strExtendableResourceId,
                    strExtendableResourceType ).entrySet( ) )
            {
                if ( mapActionHit.containsKey( entry.getKey( ) ) )
                {
                    mapActionHit.put( entry.getKey( ), mapActionHit.get( entry.getKey( ) ) + entry.getValue( ) );
                }
                else
                {
                    mapActionHit.put( entry.getKey( ), entry.getValue( ) );
                }
            }
        }

        return mapActionHit;
    }

    /**
     * Get the number of hit associated with a resource and an action name
     * @param strExtendableResourceId The id of the resource
     * @param strExtendableResourceType The type of the resource
     * @param strActionName The name of the action
     * @return The number of hit, or 0 if the resource has no hit for this
     *         action
     */
    public int getResourceActionHit( String strExtendableResourceId, String strExtendableResourceType,
            String strActionName )

    {
        int nResult = 0;
        for ( IExtendableResourceActionHitListener listener : SpringContextService
                .getBeansOfType( IExtendableResourceActionHitListener.class ) )
        {
            nResult += listener
                    .getResourceActionHit( strExtendableResourceId, strExtendableResourceType, strActionName );
        }
        return nResult;
    }

    /**
     * Notify every listeners that an action has been performed on a resource
     * @param strExtendableResourceId The id of the resource the action was
     *            performed on
     * @param strExtendableResourceType The type of the resource the action was
     *            performed on
     * @param strActionName The name of the action that was performed on the
     *            resource
     */
    public void notifyActionOnResource( String strExtendableResourceId, String strExtendableResourceType,
            String strActionName )
    {
        for ( IExtendableResourceActionHitListener listener : SpringContextService
                .getBeansOfType( IExtendableResourceActionHitListener.class ) )
        {
            listener.notifyActionOnResource( strExtendableResourceId, strExtendableResourceType, strActionName );
        }
    }
}