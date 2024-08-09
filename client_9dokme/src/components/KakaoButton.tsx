import React from 'react';

interface ButtonProps {
  onClick: () => void;
  label: string;
}

const KakaoButton: React.FC<ButtonProps> = ({ onClick, label }) => {
  return (
    <button
      onClick={onClick}
      style={{
        padding: '10px 20px',
        backgroundColor: '#FEE500',
        border: '2px solid #000',
        borderRadius: '5px',
        fontSize: '16px',
        fontWeight: 'bold',
        cursor: 'pointer',
        transition: 'background-color 0.3s ease',
      }}
      onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = '#FFD700')}
      onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = '#FEE500')}
    >
      {label}
    </button>
  );
};

export default KakaoButton;
