/*
 * Copyright (c) 2002-2010, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.util.pool.service;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;

/**
 * Wraps a connection to use {@link ConnectionPool} when closing connection.
 * @see #close()
 */
public class LuteceConnectionWrapper implements Connection
{

	private Connection _wrappee;

	private ConnectionPool _pool;

	/**
	 * Wraps a connection
	 * @param wrappee the connection to wrap
	 * @param pool the pool to use
	 */
	LuteceConnectionWrapper( Connection wrappee, ConnectionPool pool )
	{
		_wrappee = wrappee;
		_pool = pool;
	}

	/**
	 * The actual connection. Use this to close properly the connection
	 * @return the actual connection
	 */
	public Connection getWrappee()
	{
		return _wrappee;
	}

	/**
	 * DOES NOT ACTUALLY CLOSES CONNECTION, BUT CALLS _pool.freeConnection
	 * @see ConnectionPool#freeConnection(Connection)
	 */
	public void close() throws SQLException
	{
		_pool.freeConnection( this );
	}

	/**
	 * {@inheritDoc}
	 */
	public void clearWarnings() throws SQLException
	{
		_wrappee.clearWarnings();
	}

	/**
	 * {@inheritDoc}
	 */
	public void commit() throws SQLException
	{
		_wrappee.commit();
	}

	/**
	 * {@inheritDoc}
	 */
	public Array createArrayOf( String typeName, Object[] elements ) throws SQLException
	{
		return _wrappee.createArrayOf( typeName, elements );
	}

	/**
	 * {@inheritDoc}
	 */
	public Blob createBlob() throws SQLException
	{
		return _wrappee.createBlob();
	}

	/**
	 * {@inheritDoc}
	 */
	public Clob createClob() throws SQLException
	{
		return _wrappee.createClob();
	}

	/**
	 * {@inheritDoc}
	 */
	public NClob createNClob() throws SQLException
	{
		return _wrappee.createNClob();
	}

	/**
	 * {@inheritDoc}
	 */
	public SQLXML createSQLXML() throws SQLException
	{
		return _wrappee.createSQLXML();
	}

	/**
	 * {@inheritDoc}
	 */
	public Statement createStatement() throws SQLException
	{
		return _wrappee.createStatement();
	}

	/**
	 * {@inheritDoc}
	 */
	public Statement createStatement( int resultSetType, int resultSetConcurrency, int resultSetHoldability ) throws SQLException
	{
		return _wrappee.createStatement( resultSetType, resultSetConcurrency, resultSetHoldability );
	}

	/**
	 * {@inheritDoc}
	 */
	public Statement createStatement( int resultSetType, int resultSetConcurrency ) throws SQLException
	{
		return _wrappee.createStatement( resultSetType, resultSetConcurrency );
	}

	/**
	 * {@inheritDoc}
	 */
	public Struct createStruct( String typeName, Object[] attributes ) throws SQLException
	{
		return _wrappee.createStruct( typeName, attributes );
	}

	public boolean getAutoCommit() throws SQLException
	{
		return _wrappee.getAutoCommit();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getCatalog() throws SQLException
	{
		return _wrappee.getCatalog();
	}

	/**
	 * {@inheritDoc}
	 */
	public Properties getClientInfo() throws SQLException
	{
		return _wrappee.getClientInfo();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getClientInfo( String name ) throws SQLException
	{
		return _wrappee.getClientInfo( name );
	}

	/**
	 * {@inheritDoc}
	 */
	public int getHoldability() throws SQLException
	{
		return _wrappee.getHoldability();
	}

	/**
	 * {@inheritDoc}
	 */
	public DatabaseMetaData getMetaData() throws SQLException
	{
		return _wrappee.getMetaData();
	}

	/**
	 * {@inheritDoc}
	 */
	public int getTransactionIsolation() throws SQLException
	{
		return _wrappee.getTransactionIsolation();
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, Class<?>> getTypeMap() throws SQLException
	{
		return _wrappee.getTypeMap();
	}

	/**
	 * {@inheritDoc}
	 */
	public SQLWarning getWarnings() throws SQLException
	{
		return _wrappee.getWarnings();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isClosed() throws SQLException
	{
		return _wrappee.isClosed();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isReadOnly() throws SQLException
	{
		return _wrappee.isReadOnly();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isValid( int timeout ) throws SQLException
	{
		return _wrappee.isValid( timeout );
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isWrapperFor( Class<?> iface ) throws SQLException
	{
		return _wrappee.isWrapperFor( iface );
	}

	/**
	 * {@inheritDoc}
	 */
	public String nativeSQL( String sql ) throws SQLException
	{
		return _wrappee.nativeSQL( sql );
	}

	/**
	 * {@inheritDoc}
	 */
	public CallableStatement prepareCall( String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability ) throws SQLException
	{
		return _wrappee.prepareCall( sql, resultSetType, resultSetConcurrency, resultSetHoldability );
	}

	/**
	 * {@inheritDoc}
	 */
	public CallableStatement prepareCall( String sql, int resultSetType, int resultSetConcurrency ) throws SQLException
	{
		return _wrappee.prepareCall( sql, resultSetType, resultSetConcurrency );
	}

	/**
	 * {@inheritDoc}
	 */
	public CallableStatement prepareCall( String sql ) throws SQLException
	{
		return _wrappee.prepareCall( sql );
	}

	/**
	 * {@inheritDoc}
	 */
	public PreparedStatement prepareStatement( String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability ) throws SQLException
	{
		return _wrappee.prepareStatement( sql, resultSetType, resultSetConcurrency, resultSetHoldability );
	}

	/**
	 * {@inheritDoc}
	 */
	public PreparedStatement prepareStatement( String sql, int resultSetType, int resultSetConcurrency ) throws SQLException
	{
		return _wrappee.prepareStatement( sql, resultSetType, resultSetConcurrency );
	}

	/**
	 * {@inheritDoc}
	 */
	public PreparedStatement prepareStatement( String sql, int autoGeneratedKeys ) throws SQLException
	{
		return _wrappee.prepareStatement( sql, autoGeneratedKeys );
	}

	/**
	 * {@inheritDoc}
	 */
	public PreparedStatement prepareStatement( String sql, int[] columnIndexes ) throws SQLException
	{
		return _wrappee.prepareStatement( sql, columnIndexes );
	}

	/**
	 * {@inheritDoc}
	 */
	public PreparedStatement prepareStatement( String sql, String[] columnNames ) throws SQLException
	{
		return _wrappee.prepareStatement( sql, columnNames );
	}

	/**
	 * {@inheritDoc}
	 */
	public PreparedStatement prepareStatement( String sql ) throws SQLException
	{
		return _wrappee.prepareStatement( sql );
	}

	/**
	 * {@inheritDoc}
	 */
	public void releaseSavepoint( Savepoint savepoint ) throws SQLException
	{
		_wrappee.releaseSavepoint( savepoint );
	}

	/**
	 * {@inheritDoc}
	 */
	public void rollback() throws SQLException
	{
		_wrappee.rollback();
	}

	/**
	 * {@inheritDoc}
	 */
	public void rollback( Savepoint savepoint ) throws SQLException
	{
		_wrappee.rollback( savepoint );
	}

	/**
	 * {@inheritDoc}
	 */
	public void setAutoCommit( boolean autoCommit ) throws SQLException
	{
		_wrappee.setAutoCommit( autoCommit );
	}

	/**
	 * {@inheritDoc}
	 */
	public void setCatalog( String catalog ) throws SQLException
	{
		_wrappee.setCatalog( catalog );
	}

	/**
	 * {@inheritDoc}
	 */
	public void setClientInfo( Properties properties ) throws SQLClientInfoException
	{
		_wrappee.setClientInfo( properties );
	}

	/**
	 * {@inheritDoc}
	 */
	public void setClientInfo( String name, String value ) throws SQLClientInfoException
	{
		_wrappee.setClientInfo( name, value );
	}

	/**
	 * {@inheritDoc}
	 */
	public void setHoldability( int holdability ) throws SQLException
	{
		_wrappee.setHoldability( holdability );
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly( boolean readOnly ) throws SQLException
	{
		_wrappee.setReadOnly( readOnly );
	}

	/**
	 * {@inheritDoc}
	 */
	public Savepoint setSavepoint() throws SQLException
	{
		return _wrappee.setSavepoint();
	}

	/**
	 * {@inheritDoc}
	 */
	public Savepoint setSavepoint( String name ) throws SQLException
	{
		return _wrappee.setSavepoint( name );
	}

	/**
	 * {@inheritDoc}
	 */
	public void setTransactionIsolation( int level ) throws SQLException
	{
		_wrappee.setTransactionIsolation( level );
	}

	/**
	 * {@inheritDoc}
	 */
	public void setTypeMap( Map<String, Class<?>> map ) throws SQLException
	{
		_wrappee.setTypeMap( map );
	}

	/**
	 * {@inheritDoc}
	 */
	public <T> T unwrap( Class<T> iface ) throws SQLException
	{
		return _wrappee.unwrap( iface );
	}

}